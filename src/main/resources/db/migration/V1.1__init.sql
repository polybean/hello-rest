CREATE TABLE issues
(
    id          UUID not null,
    description VARCHAR(255),
    author      VARCHAR(255),
    type        INT,
    PRIMARY KEY (id)
)
