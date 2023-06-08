package br.com.fiap.cidade.service;

import br.com.fiap.cidade.dto.AddressDTO;
import br.com.fiap.cidade.dto.ProductDTO;
import br.com.fiap.cidade.model.Adress;
import br.com.fiap.cidade.model.Product;
import br.com.fiap.cidade.repository.ProductRepository;
import br.com.fiap.cidade.repository.StoreRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{
    private final ProductRepository productRepository;

    private final StoreRepository storeRepository;

    @Override
    public Product create(ProductDTO newProduct, Long idStore, String token) {

        Product product = new Product();
        product.setName(newProduct.getName());
        product.setDescription(newProduct.getDescription());
        product.setPrice(newProduct.getPrice());
        product.setStock(newProduct.getStock());
        storeRepository.findById(Math.toIntExact(idStore)).map(store
                -> {
            product.setStore(store);
            return productRepository.save(product);
        }).orElseThrow(() -> new EntityNotFoundException("Store not found"));
        return product;
    }

    @Override
    public Product update(Long id, Product newProduct, Long idStore, String token) {
        Product product = findById(id);
        if((!newProduct.getName().equals(product.getName()))&& (newProduct.getName() != null))
            product.setName(newProduct.getName());
        if((!newProduct.getDescription().equals(product.getDescription()))&& (newProduct.getDescription() != null)){
            product.setDescription(newProduct.getDescription());
        }
        if((!newProduct.getPrice().equals(product.getPrice()))&& (newProduct.getPrice() != null)){
            product.setPrice(newProduct.getPrice());
        }
        if((!newProduct.getStock().equals(product.getStock()))&& (newProduct.getStock() != null)){
            product.setStock(newProduct.getStock());
        }

        return product;
    }

    @Override
    public Product findById(Long id) {
        return productRepository.findById(Math.toIntExact(id)).orElseThrow(() -> new EntityNotFoundException("Product not found"));
    }


    @Override
    public void delete(Long id, Long idStore, String token) {
        try{
            productRepository.deleteById(Math.toIntExact(id));
        }catch(NoSuchElementException ex){
            throw new EntityNotFoundException("Product not found");
        }
    }
    @Override
    public void uploadImage(String image, Long id, Long idStore, String token) {
        Product product = findById(id);
        product.setImage(image);
        productRepository.save(product);
    }

    @Override
    public List<Product> getAllProducts(){
        return productRepository.findAll();
    }



}
