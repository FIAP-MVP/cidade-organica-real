package br.com.fiap.cidade.service;

import br.com.fiap.cidade.dto.StoreDTO;
import br.com.fiap.cidade.model.Store;

import java.util.List;

public interface StoreService {

    Store create(Store address);


    Store update(String token, StoreDTO newAdress) throws IllegalAccessException;

    Store findById(Long id);

    List<Store> findAll();
    void delete(Long id);

}
