package br.com.fiap.cidade.service;

import br.com.fiap.cidade.dto.StoreDTO;
import br.com.fiap.cidade.model.Store;
import br.com.fiap.cidade.model.User;
import br.com.fiap.cidade.repository.StoreRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;

@Service
public class StoreServiceImpl  extends JwtService implements StoreService{

    private final StoreRepository repository;

    public StoreServiceImpl(StoreRepository repository) {
        this.repository = repository;
    }


    @Override
    public Store create(StoreDTO storeDTO) {
        Store store = new Store();
        store.setName(storeDTO.getName());
        store.setCnpj(storeDTO.getCnpj());
        store.setDescription(storeDTO.getDescription());
        return repository.save(store);
    }


    @Override
    public Store update(Long id, StoreDTO newStore) throws IllegalAccessException {
        Store store = findById(id);
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

    @Override
    public Store findById(Long id) {
        return repository.findById(Math.toIntExact(id)).orElseThrow(() -> new EntityNotFoundException("Store not found"));
    }

    @Override
    public List<Store> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(Math.toIntExact(id));
    }

    @Override
    public Store uploadImage(Long id, String image) {
        Store store = findById(id);
        store.setImage(image);
        return repository.save(store);
    }
}
