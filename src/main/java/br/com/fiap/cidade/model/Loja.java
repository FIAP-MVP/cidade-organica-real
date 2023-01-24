package br.com.fiap.cidade.model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "loja")
public class Loja {
    @Column(name = "id")
    @Id
    Long id;

    @Column(name = "cnpj")
    String cnpj;

    @Column(name = "endereco")
    Endereco endereco;
    @Column(name = "nome")
    String nome;
    @Column(name = "image")
    String image;

}
