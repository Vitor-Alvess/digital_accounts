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
    personid INT NOT NULL,
    "type" CHAR NOT NULL,
    amount NUMERIC(10, 2),
    "time" TIMESTAMP NOT NULL,
    PRIMARY KEY (id),
    FOREIGN KEY (accountid) REFERENCES account(id),
    FOREIGN KEY (personid) REFERENCES person(id)
);