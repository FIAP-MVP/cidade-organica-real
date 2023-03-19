package br.com.fiap.cidade.service;

import br.com.fiap.cidade.dto.UserDTO;
import br.com.fiap.cidade.model.Store;
import br.com.fiap.cidade.model.User;
import br.com.fiap.cidade.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl extends JwtService implements UserService {
    private final UserRepository repository;


    @Value("${salt.length}")
    private int SALT_LENGTH;

    @Autowired
    private PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User update(String token, UserDTO newUser) throws IllegalAccessException {
        Long userId = getUserIdFromToken(token);
        User user = findById(userId);
        if((!newUser.getName().equals(user.getName()))&& (newUser.getName() != null)){
            user.setName(newUser.getName());
        }
        if((!newUser.getLastName().equals(user.getLastName()))&& (newUser.getLastName() != null)){
            user.setLastName(newUser.getLastName());
        }
        if((!newUser.getEmail().equals(user.getEmail()))&& (newUser.getEmail() != null)){
            user.setEmail(newUser.getEmail());
        }
        if((!newUser.getPhone().equals(user.getPhone()))&& (newUser.getPhone() != null)){
            user.setPhone(newUser.getPhone());
        }
        if(newUser.getPassword() != null){
            user.setPassword(passwordEncoder.encode(newUser.getPassword()));
        }
        return repository.save(user);
    }


    @Override
    public User findById(Long id) {
        return repository.findById(Math.toIntExact(id)).orElseThrow(() -> new EntityNotFoundException("User not found"));
    }


    @Override
    public List<User> findAll() {
        return repository.findAll();
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(Math.toIntExact(id));
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public User uploadImage(String token, String image) {
        Long userId = getUserIdFromToken(token);
        User user = findById(userId);
        user.setImage(image);
        return repository.save(user);
    }

}