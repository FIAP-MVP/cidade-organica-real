package br.com.fiap.cidade.controller;

import br.com.fiap.cidade.dto.AddressDTO;
import br.com.fiap.cidade.dto.StoreDTO;
import br.com.fiap.cidade.model.Adress;
import br.com.fiap.cidade.model.Store;
import br.com.fiap.cidade.model.User;
import br.com.fiap.cidade.service.StoreService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/store")
public class StoreController {

    @Autowired
    private StoreService service;

    @PostMapping
    public ResponseEntity<Store> save(HttpServletRequest request, @RequestBody StoreDTO store) {
        String authHeader = request.getHeader("Authorization");
        String jwt = authHeader.substring(7);
        return new ResponseEntity<>(service.create(store, jwt), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Store> update(HttpServletRequest request,@PathVariable("id") Long id,@RequestBody StoreDTO store) {
        try{
            String authHeader = request.getHeader("Authorization");
            String jwt = authHeader.substring(7);
            return new ResponseEntity<>(service.update(id,store,jwt), HttpStatus.OK);
        }catch(NoSuchElementException nsee){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(HttpServletRequest request,@PathVariable("id") Long id) {
        try{
            String authHeader = request.getHeader("Authorization");
            String jwt = authHeader.substring(7);
            service.delete(id,jwt);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(NoSuchElementException nsee){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    @GetMapping("/")
    public ResponseEntity<List<Store>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Store> findById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
        }catch(NoSuchElementException nsee){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{id}/uploadImage")
    public ResponseEntity<Void> uploadImage(HttpServletRequest request,@PathVariable("id") Long id, @RequestParam("image") MultipartFile image) throws IOException {
        try {
            String authHeader = request.getHeader("Authorization");
            String jwt = authHeader.substring(7);
            String imageBase64 = Base64.getEncoder().encodeToString(image.getBytes());
            service.uploadImage(id,imageBase64,jwt);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException nsee) {
            return ResponseEntity.notFound().build();
        }
    }

    // CRUD PRODUCT




    //todo Add product crud
    //todo Add category crud
    //todo add Address
    //todo vinculate User with Store

}
