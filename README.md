# Programação Reativa com Spring Webflux, Java e MongoDB

Este projeto é resultado do curso **Programação Reativa com Spring Webflux, Java e MongoDB** disponível na [Udemy](https://www.udemy.com). O objetivo é demonstrar conceitos de programação reativa utilizando **Spring WebFlux** integrado com **MongoDB**, com testes unitários e de integração desenvolvidos com **JUnit5** e **Mockito**.

## 🚀 Tecnologias Utilizadas

- **Java 17**  
- **Spring WebFlux**  
- **MongoDB**  
- **JUnit 5**  
- **Mockito**  
- **Gradle** 

## 🧑‍💻 Funcionalidades do Projeto

1. **Programação Reativa**: Utilização do paradigma reativo para lidar com fluxos de dados assíncronos e não bloqueantes.
2. **API CRUD**: Endpoints para criação, leitura, atualização e exclusão de dados, implementados com WebFlux e conectados ao MongoDB.
3. **MongoDB**: Banco de dados NoSQL utilizado para persistência de dados.
4. **Testes Automatizados**: 
   - **Unitários**: Testes de unidades de código com JUnit 5 e Mockito.
   - **Integração**: Testes que verificam a interação entre os componentes do sistema e o banco de dados.

## 📂 Estrutura do Projeto

- **src/main/java**: Código-fonte do projeto.
  - **controller**: Camada de controle para manipular as requisições HTTP.
  - **service**: Camada de lógica de negócios.
  - **repository**: Repositórios para interação com o MongoDB.
  - **model**: Classes de domínio e DTOs.
  
- **src/test/java**: Testes unitários e de integração.

## 🛠️ Pré-requisitos

Certifique-se de que você tenha as seguintes ferramentas instaladas em sua máquina:

- Java 17+
- MongoDB
- Gradle 

## ▶️ Como Executar

1. Clone este repositório:  
   ```bash
   git clone https://github.com/seu-usuario/seu-repositorio.git
   cd seu-repositorio

2. Configure o MongoDB:
Certifique-se de que o MongoDB está em execução na porta padrão (27017) ou ajuste as configurações no application.properties.

3. Acesse a API:
Por padrão, a API estará disponível em:
http://localhost:8080

## ✅ Testes

Execute os testes automatizados para garantir o funcionamento correto da aplicação:
./gradlew test

## 📖 Aprendizados

Durante o desenvolvimento deste projeto, foram abordados os seguintes tópicos:

Diferença entre programação reativa e imperativa.
Criação de endpoints não bloqueantes com WebFlux.
Configuração e uso do MongoDB como banco de dados reativo.
Escrita de testes unitários e de integração com JUnit5 e Mockito.
Melhores práticas no desenvolvimento de aplicações reativas.
