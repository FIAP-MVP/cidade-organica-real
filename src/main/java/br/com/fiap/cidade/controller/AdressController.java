package br.com.fiap.cidade.controller;

import br.com.fiap.cidade.model.Adress;
import br.com.fiap.cidade.service.AdressService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/adress")
public class AdressController {
    @Autowired
    private AdressService service;

    @PostMapping
    public ResponseEntity<Adress> save(HttpServletRequest request, @RequestBody Adress adress) {
        String authHeader = request.getHeader("Authorization");
        String jwt = authHeader.substring(7);
        return new ResponseEntity<>(service.create(adress, jwt), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<Adress> update(@PathVariable("id") Long id,@RequestBody Adress adress) {
        try{
            return new ResponseEntity<>(service.update(id,adress), HttpStatus.OK);
        }catch(NoSuchElementException nsee){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        try{
            service.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(NoSuchElementException nsee){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @GetMapping("/")
    public ResponseEntity<List<Adress>> findByUserId(HttpServletRequest request) {
        try {
            String authHeader = request.getHeader("Authorization");
            String jwt = authHeader.substring(7);

            return new ResponseEntity<>(service.findbyUser(jwt), HttpStatus.OK);
        }catch(NoSuchElementException nsee){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Adress> findById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
        }catch(NoSuchElementException nsee){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }




}
