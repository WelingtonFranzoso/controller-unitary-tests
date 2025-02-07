# Controller Unitary Test

[![NPM](https://img.shields.io/npm/l/react)](https://github.com/WelingtonFranzoso/controller-unitary-tests/blob/main/LICENSE) 


# Sobre o projeto

Este é um projeto Java com Spring Boot que implementa uma API para gerenciamento de pessoas, incluindo testes unitários para os controladores. O objetivo principal é demonstrar boas práticas no desenvolvimento de testes unitários para camadas de controle em aplicações Spring Boot.

# Funcionalidades

- Upload de um arquivo local
- Buscar uma  lista de pessoas por idade e profissão
- Testes automatizados da classe controller


# Tecnologias utilizadas

- Java 17+

- Spring Boot

- Maven

- JUnit e Mockito (para testes)

# Estrutura do Projeto
```
controller-unitary-tests/
├── pom.xml
├── src/
│   ├── main/java/com/franzoso/
│   │   ├── ControllerUnitaryTestsApplication.java
│   │   ├── controllers/PersonController.java
│   │   ├── entities/{Person, Occupation}.java
│   │   ├── services/PersonService.java
│   ├── main/resources/
│   │   ├── application-dev.yml
│   ├── test/java/com/franzoso/
│   │   ├── ControllerUnitaryTestsApplicationTests.java
│   │   ├── controllers/PersonControllerTest.java
```

# Como executar o projeto
## Back end
### Pré-requisitos: 
- Java 17 ou superior
- Maven (para construção do projeto)

```bash
# clonar repositório
git clone git@github.com:WelingtonFranzoso/controller-unitary-tests
.git

# entrar na pasta do projeto back end
cd controller-unitary-tests

# executar o projeto
./mvnw spring-boot:run
```

## Executando Testes

### O projeto usa JUnit e Mockito para testes.

```bash
# executar os tests
mvn test
```

# Endpoints Disponíveis

| Método | Endpoint         | Descrição                           |
|:------:|:----------------:|:-----------------------------------:|
| GET    | /test-controller | Lista pessoas por idade e profissão |
| POST   | /test-controller | Faz upload de um arquivo            |


# Contribuição

1. Fork este repositório

2. Crie uma branch (feature-nova)

3. Commit suas mudanças (git commit -m 'Add nova feature')

4. Push para sua branch (git push origin feature-nova)

5. Crie um Pull Request

# Licença

- Este projeto está sob a licença MIT. Sinta-se livre para usá-lo e modificá-lo.
