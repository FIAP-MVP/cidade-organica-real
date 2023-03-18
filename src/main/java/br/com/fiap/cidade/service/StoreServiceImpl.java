package br.com.fiap.cidade.service;

import br.com.fiap.cidade.dto.StoreDTO;
import br.com.fiap.cidade.model.Store;
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
    public Store create(Store store) {
        return repository.save(store);
    }


    @Override
    public Store update(String token, StoreDTO newAdress) throws IllegalAccessException {
        Long userId = getUserIdFromToken(token);
        Store store = findById(userId);
        Class<?> storeDTO = store.getClass();
        Field[] fields = storeDTO.getFields();
        for(Field field : fields){
            field.setAccessible(true);
            Object value = field.get(storeDTO);
            if (value != null) {
                field.set(store, value);
            }
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
}
