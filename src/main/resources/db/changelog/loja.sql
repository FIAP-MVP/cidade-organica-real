CREATE TABLE  loja (
                      id BIGINT NOT NULL AUTO_INCREMENT,
                      cnpj VARCHAR(14) NOT NULL,
                      nome VARCHAR(60) NOT NULL,
                      image LONGTEXT NOT NULL,
                      endereco_id BIGINT,
                      PRIMARY KEY (id)
);