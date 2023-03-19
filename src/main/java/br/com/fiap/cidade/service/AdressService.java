package br.com.fiap.cidade.service;


import br.com.fiap.cidade.dto.AddressDTO;
import br.com.fiap.cidade.model.Adress;

import java.util.List;
import java.util.Optional;

public interface AdressService {

    Adress create(Adress address, String token);

    Adress update(Long id, AddressDTO newAdress);
    Adress findById(Long id);
    List<Adress> findbyUser(String token);
    void delete(Long id);
}
