package br.com.fiap.cidade.controller;

import br.com.fiap.cidade.model.User;
import br.com.fiap.cidade.service.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService service;

    @PostMapping
    public ResponseEntity create(@RequestBody User user) {
        service.create(user);
        return new ResponseEntity(HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<User> update(@RequestBody User user) {
        return new ResponseEntity<>(service.update(user), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    public ResponseEntity<Optional<User>> findById(@PathVariable Long id) {
        Optional<User> user;
        try{
            user = Optional.ofNullable(service.findById(id));
            return new ResponseEntity(user,HttpStatus.OK);
        }catch(NoSuchElementException nsee){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Optional<User>> delete(@PathVariable Long id) {
        try{
            service.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        }catch(NoSuchElementException nsee){
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }



    @PatchMapping("/uploadImage")
    public ResponseEntity uploadImage(@RequestParam("image") MultipartFile image, @RequestParam("id") Long id) throws IOException {
        String imageBase64 = Base64.getEncoder().encodeToString(image.getBytes());
        service.uploadImage(id,imageBase64);
        return new ResponseEntity(HttpStatus.OK);
    }
}