package br.com.fiap.cidade.service;

import br.com.fiap.cidade.dto.AddressDTO;
import br.com.fiap.cidade.model.Adress;
import br.com.fiap.cidade.model.Product;

import java.util.List;

public interface ProductService {

    Product create(Adress address, String token);

    Product update(Long id, AddressDTO newAdress);

    Product findById(Long id);
    List<Adress> findbyUser(String token);

    void delete(Long id);
}
