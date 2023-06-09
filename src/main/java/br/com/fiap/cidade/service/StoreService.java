package br.com.fiap.cidade.service;

import br.com.fiap.cidade.dto.StoreDTO;
import br.com.fiap.cidade.model.Store;
import br.com.fiap.cidade.model.User;

import java.util.List;

public interface StoreService {

    Store create(StoreDTO store, String userId);


    Store update(Long id, StoreDTO newStore, String userId) throws IllegalAccessException;

    Store findById(Long id);


    List<Store> findAll();
    void delete(Long id, String userId);

    Store uploadImage(Long id, String image, String userId);


}
