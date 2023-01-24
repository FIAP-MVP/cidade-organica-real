package br.com.fiap.cidade.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "endereco")
public class Endereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;
    @Column(name = "cep")
    String cep;
    @Column(name = "numero")

    Integer numero;
    @Column(name = "complemento")

    String complemento;
    @Column(name = "cidade")

    String cidade;
    @Column(name = "rua")
    String rua;
    @Column(name = "identificar")
    String identificador;

    @Column(name = "usuario")
    @OneToMany
    Usuario usuario;
}
