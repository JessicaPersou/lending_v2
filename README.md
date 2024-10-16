# Lending_v2

Este repositório contém o código-fonte do Projeto Lending, que está sendo desenvolvido utilizando os princípios de **Domain-Driven Design (DDD)** e tecnologias como **Java 21**, **Spring Framework**, e o banco de dados **H2**.

## Link do Event Storming

Para uma visão geral do fluxo de eventos do projeto, você pode acessar o **Event Storming** através do seguinte link:

[Lending - Event Storming](https://miro.com/app/board/uXjVKgThZVE=/)

## Descrição do Projeto

O projeto **Lending_v2** é uma aplicação que visa gerenciar o processo de empréstimo, passando pelo cadastro de clientes, análise de crédito e formalização de contratos.

### Agregados principais

1. **Cliente**: Gerencia o cadastro e as informações pessoais dos clientes que solicitam empréstimos.
2. **Análise de Crédito**: Responsável por avaliar a capacidade do cliente de pagar o empréstimo solicitado.
3. **Contrato**: Formaliza o acordo de empréstimo, incluindo termos como valor, juros e parcelas.

### Arquitetura DDD

A arquitetura do projeto segue os princípios de **Domain-Driven Design (DDD)**, onde os conceitos do domínio de negócio são divididos em agregados, entidades e serviços. A estrutura de pacotes foi projetada para refletir esses princípios:

Cada pacote representa uma camada ou um agregado importante no projeto:

- **client**: Gerencia todas as operações relacionadas ao Cliente.
- **creditAnalysis**: Responsável pelo processo de Análise de Crédito.
- **contract**: Formaliza o contrato de empréstimo entre o cliente e a Lending.
- **common**: Contém classes e exceções comuns que podem ser utilizadas em todo o projeto.
- **infrastructure**: Configurações e integrações de infraestrutura, como o banco de dados.
- **LendingApplication**: Classe principal que inicializa o aplicativo Spring Boot.


### Tecnologias Utilizadas

- **Java 21**
- **Spring Framework**
- **H2 Database** (banco de dados em memória para desenvolvimento/testes)

## Como Executar

1. Clone o repositório:
   ```bash
   git clone https://github.com/seu-usuario/lending_v2.git

2. Navegue até a pasta do projeto:
   ```bash
   cd lending_v2

3. Execute o projeto com o Maven:
   ```bash
   ./mvnw spring-boot:run

4. O aplicativo estará disponível em:
   ```bash
    http://localhost:8080
