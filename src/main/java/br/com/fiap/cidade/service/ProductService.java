package br.com.fiap.cidade.service;

import br.com.fiap.cidade.dto.AddressDTO;
import br.com.fiap.cidade.dto.ProductDTO;
import br.com.fiap.cidade.model.Adress;
import br.com.fiap.cidade.model.Product;

import java.util.List;

public interface ProductService {

    Product create(ProductDTO newProduct, Long idStore, String token);

    Product update(Long id, Product newProduct, Long idStore, String token);

    Product findById(Long id);
    void delete(Long id, Long idStore, String token);
    void uploadImage(String image, Long id, Long idStore, String token);
    List<Product> getAllProducts();
}
