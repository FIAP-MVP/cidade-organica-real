package br.com.fiap.cidade.service;

import br.com.fiap.cidade.model.User;
import br.com.fiap.cidade.repository.UserRepository;
import br.com.fiap.cidade.utils.Argon2;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import java.security.SecureRandom;


import java.nio.charset.StandardCharsets;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    @Value("${salt.length}")
    private int SALT_LENGTH;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User create(User user) {
        user.setPassword(encryptPassword(user.getPassword()));
        return repository.save(user);
    }

    private String encryptPassword(String password) {
        char[] passwordArray = password.toCharArray();
        SecureRandom random = new SecureRandom();
        byte[] salt = new byte[SALT_LENGTH];
        random.nextBytes(salt);
        byte[] hashPassword = Argon2.hash(passwordArray, salt);
        String base64EncodedPassword = Base64.getEncoder().encodeToString(hashPassword);
        return base64EncodedPassword;
    }

    @Override
    public User update(User user) {
        user.setPassword(encryptPassword(user.getPassword()));
        return repository.save(user);
    }

    @Override
    public User findById(Long id) {
        return repository.findById(Math.toIntExact(id)).orElse(null);
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
    public User uploadImage(Long id, String image) {
        User user = repository.findById(Math.toIntExact(id)).orElseThrow(() -> new EntityNotFoundException("User not found"));
        user.setImage(image);
        return repository.save(user);
    }

}