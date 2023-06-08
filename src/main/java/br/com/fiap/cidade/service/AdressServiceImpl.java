package br.com.fiap.cidade.service;

import br.com.fiap.cidade.dto.AddressDTO;
import br.com.fiap.cidade.model.Address;
import br.com.fiap.cidade.repository.AdressRepository;
import br.com.fiap.cidade.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
@RequiredArgsConstructor
public class AdressServiceImpl implements AdressService {

    private final AdressRepository repository;

    private final UserRepository userRepository;
    private final JwtService jwtService = new JwtService();


    @Override
    public Address create(Address address, String token){
        Long userId = jwtService.getUserIdFromToken(token);
        userRepository.findById(Math.toIntExact(userId)).map(user
        -> {
            address.setUser(user);
            return repository.save(address);
        }).orElseThrow(() -> new EntityNotFoundException("User not found"));
        return address;
    }

    @Override
    public Address update(Long id, AddressDTO newAdress) {
        Address address = findById(id);
        if((!newAdress.getCep().equals(address.getCep()))&& (newAdress.getCep() != null)){
            address.setCep(newAdress.getCep());
        }
        if((!newAdress.getNumber().equals(address.getNumber()))&& (newAdress.getNumber() != null)){
            address.setNumber(newAdress.getNumber());
        }
        if((!newAdress.getComplement().equals(address.getComplement()))&& (newAdress.getComplement() != null)){
            address.setComplement(newAdress.getComplement());
        }
        if((!newAdress.getCity().equals(address.getCity()))&& (newAdress.getCity() != null)){
            address.setCity(newAdress.getCity());
        }
        if((!newAdress.getStreet().equals(address.getStreet()))&& (newAdress.getStreet() != null)){
            address.setStreet(newAdress.getStreet());
        }
        if((!newAdress.getIdentifier().equals(address.getIdentifier()))&& (newAdress.getIdentifier() != null)){
            address.setIdentifier(newAdress.getIdentifier());
        }

        return repository.save(address);
    }

    @Override
    public Address findById(Long id) {
        return repository.findById(Math.toIntExact(id)).orElseThrow(() -> new EntityNotFoundException("Adress not found"));
    }

    @Override
    public List<Address> findbyUser(String token){
        Long userId = jwtService.getUserIdFromToken(token);
        return repository.findByUserId(userId);
    }

    @Override
    public void delete(Long id) {
        try{
            repository.deleteById(Math.toIntExact(id));
        }catch(NoSuchElementException ex){
            throw new EntityNotFoundException("Adress not found");
        }
    }


}
