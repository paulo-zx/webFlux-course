package com.example.webflux.service;

import com.example.webflux.entity.User;
import com.example.webflux.mapper.UserMapper;
import com.example.webflux.model.request.UserRequest;
import com.example.webflux.repository.UserRepository;
import com.example.webflux.service.exception.ObjectNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static java.lang.String.format;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository repository;
    private final UserMapper mapper;

    public Mono<User> save(final UserRequest request){
       return repository.save(mapper.toEntity(request));

    }

    public Mono<User> findById(final String id){
      /*  return repository.findById(id)
                .switchIfEmpty(Mono.error(
                        new ObjectNotFoundException(
                                format("Object not found. Id: %s, Type: %s", id, User.class.getSimpleName())
                                )
                )); */
        return handleNotFound(repository.findById(id),id);
    }

    public Flux<User> findAll(){
        return repository.findAll();
    }

    public Mono<User> update(final String id, final UserRequest request){
        return findById(id)
                .map(entity -> mapper.toEntity(request,entity))
                .flatMap(repository::save);
    }

    public Mono<User> delete(final String id){
      /* return repository.findAndRemove(id)
                .switchIfEmpty(Mono.error(
                                new ObjectNotFoundException(
                                        format("Object not found. Id: %s, Type: %s", id, User.class.getSimpleName())
                                )
                        ));*/
        return handleNotFound(repository.findAndRemove(id),id);

    }

    private <T> Mono<T> handleNotFound(Mono<T> mono,String id){
        return mono.switchIfEmpty(Mono.error(
                new ObjectNotFoundException(
                        format("Object not found. Id: %s, Type: %s", id, User.class.getSimpleName())
                )
        ));
    }

}
