# Twitter Simples

Este projeto é uma versão simples de um sistema de gerenciamento de tweets, inspirado no Twitter. Ele foi desenvolvido usando Spring Boot e RESTful APIs, e permite que usuários criem contas, façam login e postem tweets.

## Funcionalidades

- Cadastro de usuários
- Autenticação de usuários com tokens JWT
- Criação, leitura, atualização e exclusão (CRUD) de tweets
- Feed de tweets

## Tecnologias Utilizadas

- **Java**: Linguagem de programação utilizada para o backend
- **Spring Boot**: Framework para criação de aplicações Java baseadas em Spring
- **Spring Security**: Para autenticação e autorização de usuários
- **JWT (JSON Web Token)**: Para autenticação de API com tokens
- **Hibernate**: ORM para gerenciamento do banco de dados
- **MySQL**: Banco de dados relacional utilizado
- **Maven**: Gerenciador de dependências e build
- **Git**: Controle de versão

## Requisitos

- Java 17+
- Maven 3.8.1+
- MySQL 8+
- Postman (opcional, para testar as APIs)

## Instalação e Execução

### 1. Clonar o repositório
```
git clone https://github.com/Gustav0-12/twitter-simples.git
```
### 2. Configurar o banco de dados
Certifique-se de ter o MySQL instalado e rodando. Crie um banco de dados chamado twitter_simples:
```
CREATE DATABASE twitter_simples;
```
Atualize as credenciais do banco de dados no arquivo src/main/resources/application.properties    
```
spring.datasource.url=jdbc:mysql://localhost:3306/twitter_simples
spring.datasource.username=seu_usuario
spring.datasource.password=sua_senha
```
### 3. Compilar e rodar a aplicação
No diretório do projeto, execute os seguintes comandos para compilar e rodar o projeto:
```
mvn clean install
mvn spring-boot:run
```
### 4. Endpoints principais

### POST /auth/register: Registra um novo usuário.
### POST /auth/login: Autentica um usuário e retorna um token JWT.
### GET  /api/users: Lista todos os usúarios(apenas admin).
### GET  /api/users/{id}: Lista um usúario específico (apenas admin).
### PATCH /api/users/password/{id}: Alterar senha de usúario. (autenticado)
### GET  /api/tweets: Lista todos os tweets. (autenticado).
### POST /api/tweets/criar: Cria um novo tweet (autenticado).
### PATCH /api/tweets/update/{id}: Atualiza um tweet(autenticado)
### DELETE /api/tweets/{id}: Exclui um tweet específico (autenticado).

