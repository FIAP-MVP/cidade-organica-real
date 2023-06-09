package br.com.fiap.cidade.controller;

import br.com.fiap.cidade.dto.StoreDTO;
import br.com.fiap.cidade.model.Store;
import br.com.fiap.cidade.service.StoreService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/store")
@Api(tags = "Store", description = "Endpoints para gerenciamento de lojas")
public class StoreController {

    @Autowired
    private StoreService service;

    @PostMapping
    @ApiOperation("Cria uma nova loja")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Loja criada com sucesso"),
            @ApiResponse(code = 400, message = "Requisição inválida"),
            @ApiResponse(code = 500, message = "Erro interno do servidor")
    })
    public ResponseEntity<Store> save(HttpServletRequest request, @RequestBody StoreDTO store) {
        return new ResponseEntity<>(service.create(store), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @ApiOperation("Atualiza uma loja existente")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Loja atualizada com sucesso"),
            @ApiResponse(code = 400, message = "Requisição inválida"),
            @ApiResponse(code = 404, message = "Loja não encontrada"),
            @ApiResponse(code = 500, message = "Erro interno do servidor")
    })
    public ResponseEntity<Store> update(@PathVariable("id") Long id, @RequestBody StoreDTO store) {
        try {
            return new ResponseEntity<>(service.update(id, store), HttpStatus.OK);
        } catch (NoSuchElementException nsee) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Exclui uma loja existente")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Loja excluída com sucesso"),
            @ApiResponse(code = 404, message = "Loja não encontrada"),
            @ApiResponse(code = 500, message = "Erro interno do servidor")
    })
    public ResponseEntity<HttpStatus> delete(@PathVariable("id") Long id) {
        try {
            service.delete(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (NoSuchElementException nsee) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/")
    @ApiOperation("Obtém todas as lojas")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Lojas encontradas com sucesso"),
            @ApiResponse(code = 404, message = "Lojas não encontradas"),
            @ApiResponse(code = 500, message = "Erro interno do servidor")
    })
    public ResponseEntity<List<Store>> findAll() {
        return new ResponseEntity<>(service.findAll(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @ApiOperation("Obtém uma loja pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Loja encontrada com sucesso"),
            @ApiResponse(code = 404, message = "Loja não encontrada"),
            @ApiResponse(code = 500, message = "Erro interno do servidor")
    })
    public ResponseEntity<Store> findById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
        } catch (NoSuchElementException nsee) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PatchMapping("/{id}/uploadImage")
    @ApiOperation("Faz o upload de uma imagem para a loja")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Upload de imagem realizado com sucesso"),
            @ApiResponse(code = 400, message = "Requisição inválida"),
            @ApiResponse(code = 404, message = "Loja não encontrada"),
            @ApiResponse(code = 500, message = "Erro interno do servidor")
    })
    public ResponseEntity<Void> uploadImage(@PathVariable("id") Long id, @RequestParam("image") MultipartFile image) throws IOException {
        try {
            String imageBase64 = Base64.getEncoder().encodeToString(image.getBytes());
            service.uploadImage(id, imageBase64);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException nsee) {
            return ResponseEntity.notFound().build();
        }
    }
}
