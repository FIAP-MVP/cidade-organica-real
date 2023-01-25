
CREATE TABLE  endereco (
                          id BIGINT NOT NULL AUTO_INCREMENT,
                          cep VARCHAR(255) NOT NULL,
                          numero INTEGER NOT NULL,
                          complemento VARCHAR(255) NOT NULL,
                          cidade VARCHAR(255) NOT NULL,
                          rua VARCHAR(255) NOT NULL,
                          identificador VARCHAR(255) NOT NULL,
                          usuario_id BIGINT NOT NULL,
                          PRIMARY KEY (id)
)



