# Contas digitais
    Este projeto simula um sistema de contas digitais, com operações de saques e depósitos.

Banco de Dados Utilizado: PostgreSQL

Para inicializar o sistema, é necessário criar um arquivo .env na raiz do projeto e formatá-lo dessa maneira:

```
DB_URL = <URL DO BANCO> 
DB_USERNAME = <USUÁRIO>
DB_PASSWORD = <SENHA>
```

## M-ER: Modelo Entidade-Relacionamento
Abaixo estão descritas as entidades e seus respectivos atributos utilizados nesse projeto. </br>

```
Person
 - ID: identificador padrão,
 - CPF: documento da pessoa,
 - Name: nome da pessoa.
 - Gender: sexo (masculino ou feminino) da pessoa.
 - Birth: data de nascimento da pessoa.
 - Ocupation: profissão da pessoa.
 - ModifiedAtt: Data e hora da utlimá alteração nessa pessoa.
 - Active: determina se a pessoa está ativa ou não no sistema.
 
 
 Account
 - ID: identificador padrão.
 - Agency: agência da conta.
 - Number: número da conta.
 - Tipo: tipo da conta (Poupança ou Corrente).
 - TotalAmount: quantia monetária na conta.
 - Currency: câmbio da conta (Real ou Dólar).
 - PersonId: identificador da pessoa titular da conta.
 - ModifiedAt: Data e hora da utlimá alteração nessa conta.
 - Active: determina se a conta está ativa ou não no sistema.
 
 
 Operation
 - ID: identificador padrão.
 - AccountId: identificador da conta que recebeu essa operação.
 - Type: tipo de operação (Saque ou Depósito).
 - Amount: valor utilizado na operação para ser sacado ou depositado.
 - Time: Data e Hora que a operação ocorreu.
```

Regras de negócio:
- Uma pessoa pode possuir várias contas, mas uma conta só possui uma pessoa titular.
- Uma conta pode realizar várias operações, mas cada operação está ligada a somente uma conta.
- Se uma conta for "deletada" (ou desativada), não deve ser mais possível acessar as operações ligadas a ela.
- Se uma pessoa for "deletada" (ou desativada), suas respectivas contas também são desativadas.
- Dados de cpf, agência e número da conta não serão formatados na base de dados, mas devem sempre estar formatados quando forem apresentados ao usuário.
- A quantia de uma operação interfere diretamente no valor armazenado na sua conta relacionada.
- O câmbio da moeda na conta interfere no valor armazenado por ela

## Diagrama Entidade-Relacionamento
![img.png](img.png)

## Scripts DDL
```
CREATE TABLE IF NOT EXISTS person (
    id SERIAL,
    cpf VARCHAR(12) UNIQUE NOT NULL,
    "name" VARCHAR(50) NOT NULL,
    gender CHAR NOT NULL,
    birth DATE NOT NULL,
    ocupation VARCHAR(100) NOT NULL,
    modified_at TIMESTAMP DEFAULT NOW(),
    active BOOLEAN NOT NULL DEFAULT TRUE,
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS account (
    id SERIAL,
    agency VARCHAR(5) NOT NULL,
    "number" VARCHAR(6) NOT NULL,
    "type" CHAR NOT NULL,
    totalamount NUMERIC(10,2) NOT NULL DEFAULT 0,
    currency CHAR NOT NULL,
    personid INT NOT NULL,
    modified_at TIMESTAMP DEFAULT NOW(),
    active BOOLEAN NOT NULL DEFAULT TRUE,
    UNIQUE (agency, "number"),
    PRIMARY KEY (id),
    FOREIGN KEY (personid) REFERENCES person(id)
);

CREATE TABLE IF NOT EXISTS operation (
    id SERIAL,
    accountid INT NOT NULL,
    "type" CHAR NOT NULL,
    amount NUMERIC(10, 2),
    "time" TIMESTAMP NOT NULL DEFAULT NOW(),
    PRIMARY KEY (id),
    FOREIGN KEY (accountid) REFERENCES account(id)
);
```

## Scripts DML
Todos os scripts DML estão localizados nos arquivos `repositories`, 
na pasta `src/main/java/com/alves/vitor/DigitalAccounts/infra/persistence` </br> 
Foi utilizado JDBC no lugar convencional de JPA para demonstrar consultas puras em SQL.

## Endpoints
O sistema está com o Swagger integrado, basta visitar ``http://localhost:8080/digital-accounts/api/swagger-ui/index.html`` </br>
para se ter noção das requisições e respostas esperadas.