package br.com.fiap.cidade.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "store")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "cnpj")
    private String cnpj;

    @OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="store")
    private Adress address;

    @Column(name = "name")
    private String name;

    @Column(name = "image")
    private String image;

    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products = new ArrayList<>();

    // métodos para adicionar e remover produtos
    public void addProduct(Product product) {
        products.add(product);
        product.setStore(this);
    }

    public void removeProduct(Product product) {
        products.remove(product);
        product.setStore(null);
    }

}
