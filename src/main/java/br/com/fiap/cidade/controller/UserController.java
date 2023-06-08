package br.com.fiap.cidade.controller;

import br.com.fiap.cidade.dto.UserDTO;
import br.com.fiap.cidade.model.User;
import br.com.fiap.cidade.service.UserService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import jakarta.servlet.http.HttpServletRequest;
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
@RequestMapping("/api/v1/user")
public class UserController {

    @Autowired
    private UserService service;


    @PutMapping
    @ApiOperation(value = "Update a user")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User updated successfully"),
            @ApiResponse(code = 404, message = "User not found")
    })
    public ResponseEntity<User> update(HttpServletRequest request, @RequestBody UserDTO user) throws IllegalAccessException {
        String authHeader = request.getHeader("Authorization");
        String jwt = authHeader.substring(7);
        return new ResponseEntity<>(service.update(jwt, user), HttpStatus.OK);
    }


    @GetMapping("/{id}")
    @ApiOperation(value = "Get a user by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User found"),
            @ApiResponse(code = 404, message = "User not found")
    })
    public ResponseEntity<Optional<User>> findById(@PathVariable Long id) {
        Optional<User> user;
        try {
            user = Optional.ofNullable(service.findById(id));
            return new ResponseEntity(user, HttpStatus.OK);
        } catch (NoSuchElementException nsee) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping
    @ApiOperation(value = "Get all users")
    @ApiResponse(code = 200, message = "Users retrieved successfully")
    public ResponseEntity<List<User>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @ApiOperation(value = "Delete a user by ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "User deleted successfully"),
            @ApiResponse(code = 404, message = "User not found")
    })
    public ResponseEntity<Optional<User>> delete(@PathVariable Long id) {
        try {
            service.delete(id);
            return new ResponseEntity(HttpStatus.OK);
        } catch (NoSuchElementException nsee) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/uploadImage")
    @ApiOperation(value = "Upload user image")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Image uploaded successfully"),
            @ApiResponse(code = 404, message = "User not found")
    })
    public ResponseEntity<Void> uploadImage(HttpServletRequest request, @RequestParam("image") MultipartFile image) throws IOException {
        try {
            String authHeader = request.getHeader("Authorization");
            String jwt = authHeader.substring(7);
            String imageBase64 = Base64.getEncoder().encodeToString(image.getBytes());
            service.uploadImage(jwt, imageBase64);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException nsee) {
            return ResponseEntity.notFound().build();
        }
    }
}
