package br.com.fiap.cidade.service;

import br.com.fiap.cidade.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User update(User user);
    User findById(Long id);
    List<User> findAll();
    void delete(Long id);
    Optional<User> findByEmail(String email);
    User uploadImage(String token, String image);
}
