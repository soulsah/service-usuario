# Hackathon 5SOAT

Este repositório contém o código-fonte e a documentação para o Hackathon 5SOAT/2024, desenvolvido pelo Grupo 70.

O projeto consiste em uma sistema de agendamento de consultas médicas utilizando uma arquitetura com microserviços. Além do Spring Framework, Spring Boot e Sping Data, também utilizamos neste projeto as tecnologias da AWS, sendo elas ECS e API Gateway.

1- Relatório Técnico
Tecnologias e ferramentas utilizadas

Linguagem de programação:

- Java 17

Framework:

- Spring Boot 3.3.4

Bibliotecas:

- Spring Web
- OpenAPI
- Lombok
- AWS API Gateway
- AWS ECS

Banco de dados:

- AWS DynamoDB

Outras ferramentas:

- Docker

# Configurações da solução

## Arquitetura

Para esta solução, escolhemos a arquitetura hexagonal (ports and adapters), pois, com ela, temos o total isolamento da lógica de negócio de outros componentes da aplicação, como banco de dados, apis externas e interfaces com o usuário, permitindo assim que a lógica de negócio se mantenha consistente e reutilizável, independentemente das mudanças nas tecnologias externas.
Essa abordagem nos proporciona uma estrutura modular, escalável, testável e de fácil manutenção. Haja visto que em um ambiente de microservices, onde mudanças, integrações e atualizações são constantes, a arquitetura hexagonal é uma escolha robusta que promove isolamento de responsabilidades e flexibilidade ao longo da vida útil dos serviços.
