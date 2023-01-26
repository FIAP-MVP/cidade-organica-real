package br.com.fiap.cidade.service;

import br.com.fiap.cidade.model.User;
import br.com.fiap.cidade.repository.UserRepository;
import de.mkammerer.argon2.Argon2;
import org.springframework.stereotype.Service;
import de.mkammerer.argon2.Argon2Factory;


import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    private final UserRepository repository;

    public UserServiceImpl(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public User create(User user) {
        user.setPassword(encryptPassword(user.getPassword()));
        return repository.save(user);
    }

    private byte[] encryptPassword(byte[] password) {
        byte[] passwordBytes = password;
        Argon2 argon2 = Argon2Factory.create(Argon2Factory.Argon2Types.ARGON2id);
        String hash = argon2.hash(12, 65536, 1, passwordBytes);
        Arrays.fill(passwordBytes, (byte) 0);// limpa o array de bytes após a criptografia
        return hash.getBytes(StandardCharsets.UTF_8);
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
        User user = repository.findById(Math.toIntExact(id)).orElse(null);
        if (user != null) {
            user.setImage(image);
            return repository.save(user);
        } else {
            return null;
        }
    }

}