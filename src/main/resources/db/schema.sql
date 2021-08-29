DROP TABLE IF EXISTS account;

CREATE TABLE account (
    id       VARCHAR(20),
    password VARCHAR(30) NOT NULL,
    PRIMARY KEY (id)
);
