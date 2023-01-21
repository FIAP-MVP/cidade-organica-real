package br.com.fiap.cidade.model;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    Long id;

    @Column(name = "nome")
    String nome;
    @Column(name = "sobrenome")
    String sobrenome;
    @Column(name = "cpf")
    String cpf;
    @Column(name = "email")
    String email;
    @Column(name = "telefone")
    String telefone;

    @Column(name = "endereco")
    @ManyToOne
    List<Endereco> endereco;

    @Column(name = "image")
    String image;
}
