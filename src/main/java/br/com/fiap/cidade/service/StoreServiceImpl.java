package br.com.fiap.cidade.service;

import br.com.fiap.cidade.dto.StoreDTO;
import br.com.fiap.cidade.model.Store;
import br.com.fiap.cidade.model.User;
import br.com.fiap.cidade.repository.StoreRepository;
import br.com.fiap.cidade.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

@Service
public class StoreServiceImpl  extends JwtService implements StoreService{

    private final StoreRepository repository;
    private final UserRepository userRepository;


    public StoreServiceImpl(StoreRepository repository, UserRepository userRepository) {
        this.repository = repository; this.userRepository = userRepository;
    }

    private final JwtService jwtService = new JwtService();



    @Override
    public Store create(StoreDTO storeDTO, String userId) {
        try{
            Long id = jwtService.getUserIdFromToken(userId);
            Store store = new Store();
            userRepository.findById(Math.toIntExact(id)).map(user -> {
                store.setName(storeDTO.getName());
                store.setCnpj(storeDTO.getCnpj());
                store.setDescription(storeDTO.getDescription());
                store.setOwner(user);
                return repository.save(store);
            });
            return store;
        }catch(Exception ex){
            throw ex;
        }

    }


    @Override
    public Store update(Long id, StoreDTO newStore, String token) throws IllegalAccessException {

        try{
            Store store = findById(id);
            Long userId = jwtService.getUserIdFromToken(token);
            if(userId.equals(store.getOwner().getId())){
                if((!newStore.getName().equals(store.getName()))&& (newStore.getName() != null)){
                    store.setName(newStore.getName());
                }
                if((!newStore.getCnpj().equals(store.getCnpj()))&& (newStore.getCnpj() != null)){
                    store.setCnpj(newStore.getCnpj());
                }
                if((!newStore.getDescription().equals(store.getDescription()))&& (newStore.getDescription() != null)){
                    store.setDescription(newStore.getDescription());
                }
                return repository.save(store);
            }
            else{
                throw new IllegalStateException();
            }
        }catch(Exception ex){
            throw ex;
        }


    }

    @Override
    public Store findById(Long id) {
        try{
            return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Store not found"));
        }catch(Exception ex){
            throw ex;
        }
    }

    @Override
    public List<Store> findAll() {
        try{
            return repository.findAll();
        }catch(Exception ex){
            throw ex;
        }

    }


    @Override
    public void delete(Long id, String token) {
        try{
            Long userId = jwtService.getUserIdFromToken(token);
            Store store = findById(id);
            if(userId.equals(store.getOwner().getId())){
                repository.deleteById(id);
            }
            else{
                throw new IllegalStateException();
            }
        }catch(Exception ex){
            throw ex;
        }

    }

    @Override
    public Store uploadImage(Long id, String image, String token) {
        try{
            Store store = findById(id);
            Long userId = jwtService.getUserIdFromToken(token);
            if(userId.equals(store.getOwner().getId())){
                store.setImage(image);
                return repository.save(store);
            }
            else{
              throw new IllegalStateException();
            }
        }
        catch(EntityNotFoundException ex){
            throw new EntityNotFoundException(ex);
        }

    }
}
