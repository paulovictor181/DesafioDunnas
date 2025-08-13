### DesafioDunnas

#### Visão Geral

DesafioDunnas é um sistema de marketplace desenvolvido com Spring Boot, projetado para gerenciar diferentes perfis de usuário: `CLIENTE`, `FORNECEDOR` e `ADMIN`. A aplicação permite que clientes realizem compras, fornecedores gerenciem seus produtos e acompanhem suas vendas, e administradores supervisionem os usuários do sistema.

#### Tecnologias

O projeto foi construído utilizando as seguintes tecnologias principais:

  * **Backend**: Spring Boot, Spring Security, Spring Data JPA, Java 17
  * **Banco de Dados**: PostgreSQL com Flyway para migrações
  * **Frontend**: JSP, JSTL, Bootstrap 5.3.7 (via WebJars)
  * **Desenvolvimento**: Lombok, Maven

#### Funcionalidades

##### Cliente

  * **Autenticação**: O cliente pode se registrar e fazer login para acessar sua área restrita.
  * **Pedidos**: Pode visualizar fornecedores e seus produtos, criar novos pedidos, pagar e cancelar pedidos pendentes.
  * **Saldo**: Pode adicionar saldo à sua conta, escolhendo entre diferentes métodos de pagamento.
  * **Relatórios**: Acessa relatórios de histórico de compras e de créditos.

##### Fornecedor

  * **Autenticação**: Acessa sua área restrita após o login.
  * **Produtos**: Gerencia seus produtos, com a capacidade de adicionar novos e filtrar os existentes.
  * **Relatórios**: Visualiza um relatório de vendas com a opção de filtrar por período.

##### Administrador

  * **Autenticação**: Acessa sua área restrita após o login.
  * **Usuários**: Visualiza uma lista de todos os usuários cadastrados e pode filtrá-los por nome de usuário.

#### Estrutura do Banco de Dados

O banco de dados é inicializado e gerenciado através de migrações Flyway localizadas em `src/main/resources/db/migration`. As tabelas principais incluem:

  * `usuarios`: Armazena os dados de login e o tipo de usuário (`CLIENTE`, `FORNECEDOR`, `ADMIN`).
  * `clientes`: Detalhes do cliente, incluindo nome, CPF, data de nascimento e saldo.
  * `fornecedores`: Detalhes do fornecedor, como nome e CNPJ.
  * `produtos`: Produtos oferecidos pelos fornecedores, com preço e status.
  * `pedidos`: Pedidos feitos pelos clientes, com status e valor total.
  * `itens_pedido`: Relação dos produtos em cada pedido.
  * `pagamentos`: Histórico de créditos adicionados pelos clientes.

#### Soft Delete

Para manter um histórico completo de transações e produtos, o projeto utiliza a abordagem de "soft delete" nas entidades `pedidos` e `produtos`.

  * **Pedidos**: Em vez de serem excluídos, os pedidos são marcados como `CANCELADO` ou `CONCLUIDO` através de funções nativas do PostgreSQL. Isso permite que o histórico de pedidos seja sempre acessível nos relatórios, independentemente do seu estado final.
  * **Produtos**: A tabela `produtos` possui uma coluna `status` com o valor padrão `ATIVO`. Quando um fornecedor quer "remover" um produto, seu status é alterado para `INATIVO` (o que pode ser deduzido pelo `enum` `StatusProduto`), e o sistema garante que apenas produtos com status `ATIVO` sejam listados para a criação de novos pedidos. Isso preserva o registro do produto para referência em pedidos passados.

#### Como Iniciar o Projeto

1.  **Pré-requisitos**:
      * Java 17 JDK
      * PostgreSQL
      * Apache Maven
2.  **Configurar o Banco de Dados**:
      * Crie um banco de dados PostgreSQL.
      * Edite o arquivo `src/main/resources/application.properties` com suas credenciais do banco de dados.
3.  **Executar a Aplicação**:
      * Abra um terminal na raiz do projeto.
      * Execute a aplicação com o Maven Wrapper:
        ```sh
        ./mvnw spring-boot:run
        ```
      * O Flyway se encarregará de executar as migrações do banco de dados na inicialização.
      * Na primeira execução, um usuário `admin` com a senha `admin` será criado automaticamente.
4.  **Acessar a Aplicação**:
      * Abra o navegador e acesse `http://localhost:8080`.
      * Você será redirecionado para a página de login.