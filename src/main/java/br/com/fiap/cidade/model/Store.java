package br.com.fiap.cidade.model;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Table(name = "store")
public class Store {
    @Column(name = "id")
    @Id
    private Long id;

    @Column(name = "cnpj")
    private String cnpj;

    @Column(name = "adress")
    private Adress adress;

    @Column(name = "name")
    private String name;

    @Column(name = "image")
    private String image;

}
