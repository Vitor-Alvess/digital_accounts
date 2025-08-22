CREATE TABLE IF NOT EXISTS person (
    id SERIAL,
    cpf VARCHAR(12) NOT NULL,
    rg VARCHAR(10) NOT NULL,
    "name" VARCHAR(50) NOT NULL,
    gender CHAR NOT NULL,
    birth DATE NOT NULL,
    ocupation VARCHAR(100) NOT NULL,
    PRIMARY KEY(id)
);

CREATE TABLE IF NOT EXISTS account (
    id SERIAL,
    agency VARCHAR(5) NOT NULL,
    "number" VARCHAR(6) NOT NULL,
    "type" CHAR NOT NULL,
    ammount NUMERIC(10,2) NOT NULL,
    currency CHAR NOT NULL,
    personid INT NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (personid) REFERENCES person(id)
);

CREATE TABLE IF NOT EXISTS operation (
    id SERIAL,
    accountid INT NOT NULL,
    personid INT NOT NULL,
    "time" TIMESTAMP NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (accountid) REFERENCES account(id),
    FOREIGN KEY (personid) REFERENCES person(id)
);