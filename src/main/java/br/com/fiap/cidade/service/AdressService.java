package br.com.fiap.cidade.service;


import br.com.fiap.cidade.dto.AddressDTO;
import br.com.fiap.cidade.model.Address;

import java.util.List;

public interface AdressService {

    Address create(Address address, String token);

    Address update(Long id, AddressDTO newAdress);
    Address findById(Long id);
    List<Address> findbyUser(String token);
    void delete(Long id);
}
