package br.com.fiap.cidade.service;

import br.com.fiap.cidade.model.Adress;
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
    public Adress create(Adress adress, String token){
        Long userId = jwtService.getUserIdFromToken(token);
        userRepository.findById(Math.toIntExact(userId)).map(user
        -> {
            adress.setUser(user);
            return repository.save(adress);
        }).orElseThrow(() -> new EntityNotFoundException("User not found"));
        return adress;
    }

    @Override
    public Adress update(Long id,Adress newAdress) {
        return repository.findById(Math.toIntExact(id)).map(adress -> {
            adress = newAdress;
            return repository.save(adress);
        }).orElseThrow(() -> new EntityNotFoundException("Adress not found"));

    }

    @Override
    public Adress findById(Long id) {
        return repository.findById(Math.toIntExact(id)).orElseThrow(() -> new EntityNotFoundException("Adress not found"));
    }

    @Override
    public List<Adress> findbyUser(String token){
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
