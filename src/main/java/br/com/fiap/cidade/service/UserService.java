package br.com.fiap.cidade.service;

import br.com.fiap.cidade.dto.UserDTO;
import br.com.fiap.cidade.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    User update(String token, UserDTO newUser) throws IllegalAccessException;
    User findById(Long id);
    List<User> findAll();
    void delete(Long id);
    Optional<User> findByEmail(String email);
    User uploadImage(String token, String image);
}
