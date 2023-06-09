package br.com.fiap.cidade.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Getter
@Setter
@Table(name = "store")
public class Store {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "cnpj")
    private String cnpj;

    @Column(name = "description")
    private String description;
    @OneToOne(cascade=CascadeType.ALL, fetch=FetchType.LAZY, mappedBy="store")
    private Adress address;
    @Column(name = "name")
    private String name;

    @Column(name = "image")
    private String image;

    @JsonManagedReference
    @OneToMany(mappedBy = "store", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Product> products = new ArrayList<>();
    @JsonIgnore
    @OneToOne
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User owner;


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
