CREATE TABLE usuario (
                         id BIGINT NOT NULL AUTO_INCREMENT,
                         nome VARCHAR(255) NOT NULL,
                         sobrenome VARCHAR(255) NOT NULL,
                         cpf VARCHAR(11) NOT NULL,
                         email VARCHAR(100) NOT NULL,
                         telefone VARCHAR(10) NOT NULL,
                         image LONGTEXT NOT NULL,
                         endereco_id BIGINT,
                         PRIMARY KEY (id)
)
