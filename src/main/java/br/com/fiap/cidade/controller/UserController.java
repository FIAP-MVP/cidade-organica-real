package br.com.fiap.cidade.controller;

import br.com.fiap.cidade.model.User;
import br.com.fiap.cidade.service.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    public User create(@RequestBody User user) {
        return service.create(user);
    }

    @PutMapping
    public User update(@RequestBody User user) {
        return service.update(user);
    }

    @GetMapping("/{id}")
    public User findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @GetMapping
    public List<User> findAll() {
        return service.findAll();
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        service.delete(id);
    }

    @GetMapping("/email/{email}")
    public Optional<User> findByEmail(@PathVariable String email) {
        return service.findByEmail(email);
    }

    @PatchMapping("/uploadImage")
    public User uploadImage(@RequestParam("image") MultipartFile image, @RequestParam("id") Long id) throws IOException {
        String imageBase64 = Base64.getEncoder().encodeToString(image.getBytes());
        return service.uploadImage(id,imageBase64);
    }
}