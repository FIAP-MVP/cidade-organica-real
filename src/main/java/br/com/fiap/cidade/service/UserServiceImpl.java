package br.com.fiap.cidade.service;

import br.com.fiap.cidade.model.User;
import br.com.fiap.cidade.repository.UserRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


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
    public User update(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
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
    public User uploadImage(String token, String image) {
        Long userId = getUserIdFromToken(token);
        User user = repository.findById(Math.toIntExact(userId)).orElseThrow(() -> new EntityNotFoundException("User not found"));
        user.setImage(image);
        return repository.save(user);
    }

}