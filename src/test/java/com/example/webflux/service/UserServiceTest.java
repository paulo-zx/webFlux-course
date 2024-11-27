package com.example.webflux.service;

import com.example.webflux.entity.User;
import com.example.webflux.mapper.UserMapper;
import com.example.webflux.model.request.UserRequest;
import com.example.webflux.repository.UserRepository;
import com.example.webflux.service.exception.ObjectNotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.util.Objects;

import static java.lang.String.format;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private UserRepository repository;

    @Mock
    private UserMapper mapper;

    @InjectMocks
    private UserService service;

    @Test
    void testSave() {
        UserRequest request =new UserRequest("Paulo","paulo@emailteste.com","123");
        User entity = User.builder().build();

        Mockito.when(mapper.toEntity(any(UserRequest.class))).thenReturn(entity);
        Mockito.when(repository.save(any(User.class))).thenReturn(Mono.just(User.builder().build()));

        Mono<User> result = service.save(request);

        StepVerifier.create(result)
                //.expectNextMatches(Objects::nonNull)
                .expectNextMatches(user -> user.getClass() == User.class)
                .expectComplete()
                .verify();

        Mockito.verify(repository, times(1)).save(any(User.class));

    }

    @Test
    void testFindById(){
        when(repository.findById(anyString())).thenReturn(Mono.just(User.builder()
                .id("1234")
                .build()));

        Mono<User> result = service.findById("123");

        StepVerifier.create(result)
                .expectNextMatches(user -> user.getClass() == User.class)
               // && user.getId() == "1234")
                .expectComplete()
                .verify();

        Mockito.verify(repository, times(1)).findById(anyString());

    }

    @Test
    void testFindAll(){
        when(repository.findAll()).thenReturn(Flux.just(User.builder().build()));

        Flux<User> result = service.findAll();

        StepVerifier.create(result)
                .expectNextMatches(user -> user.getClass() == User.class)
                .expectComplete()
                .verify();

        Mockito.verify(repository, times(1)).findAll();

    }

    @Test
    void testUpdate(){
        UserRequest request =new UserRequest("Paulo","paulo@emailteste.com","123");
        User entity = User.builder().build();

        when(mapper.toEntity(any(UserRequest.class), any(User.class))).thenReturn(entity);
        when(repository.findById(anyString())).thenReturn(Mono.just(entity));
        when(repository.save(any(User.class))).thenReturn(Mono.just(entity));

        Mono<User> result = service.update("123", request);

        StepVerifier.create(result)
                //.expectNextMatches(Objects::nonNull)
                .expectNextMatches(user -> user.getClass() == User.class)
                .expectComplete()
                .verify();

        Mockito.verify(repository, times(1)).save(any(User.class));
    }

    @Test
    void testDelete(){
        User entity = User.builder().build();
        when(repository.findAndRemove(anyString())).thenReturn(Mono.just(entity));

        Mono<User> result = service.delete("123");

        StepVerifier.create(result)
                //.expectNextMatches(Objects::nonNull)
                .expectNextMatches(user -> user.getClass() == User.class)
                .expectComplete()
                .verify();

        Mockito.verify(repository, times(1)).findAndRemove(anyString());
    }

    @Test
    void testHanddleNotFound(){
        when(repository.findById(anyString())).thenReturn(Mono.empty());

        try{
            service.findById("123").block();
        } catch (Exception ex){
            Assertions.assertEquals(ObjectNotFoundException.class,ex.getClass());
            assertEquals(format("Object not found. Id: %s, Type: %s", "123", User.class.getSimpleName()),
                    ex.getMessage());
        }
    }
}