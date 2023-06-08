package br.com.fiap.cidade.controller;

import br.com.fiap.cidade.dto.AddressDTO;
import br.com.fiap.cidade.model.Address;
import br.com.fiap.cidade.service.AdressService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/v1/address")
@Api(tags = "Address", description = "Endpoints para gerenciamento de endereços")
public class AddressController {
    @Autowired
    private AdressService service;

    @PostMapping
    @ApiOperation("Cria um novo endereço")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Endereço criado com sucesso"),
            @ApiResponse(code = 400, message = "Requisição inválida"),
            @ApiResponse(code = 401, message = "Não autorizado"),
            @ApiResponse(code = 500, message = "Erro interno do servidor")
    })
    public ResponseEntity<Address> save(HttpServletRequest request, @RequestBody Address address) {
        String authHeader = request.getHeader("Authorization");
        String jwt = authHeader.substring(7);
        return new ResponseEntity<>(service.create(address, jwt), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    @ApiOperation("Atualiza um endereço existente")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Endereço atualizado com sucesso"),
            @ApiResponse(code = 400, message = "Requisição inválida"),
            @ApiResponse(code = 401, message = "Não autorizado"),
            @ApiResponse(code = 404, message = "Endereço não encontrado"),
            @ApiResponse(code = 500, message = "Erro interno do servidor")
    })
    public ResponseEntity<Address> update(@PathVariable("id") Long id, @RequestBody AddressDTO address) {
        try {
            return new ResponseEntity<>(service.update(id, address), HttpStatus.OK);
        } catch (NoSuchElementException nsee) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    @ApiOperation("Exclui um endereço existente")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Endereço excluído com sucesso"),
            @ApiResponse(code = 401, message = "Não autorizado"),
            @ApiResponse(code = 404, message = "Endereço não encontrado"),
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
    @ApiOperation("Obtém todos os endereços do usuário")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Endereços encontrados com sucesso"),
            @ApiResponse(code = 401, message = "Não autorizado"),
            @ApiResponse(code = 404, message = "Endereços não encontrados"),
            @ApiResponse(code = 500, message = "Erro interno do servidor")
    })
    public ResponseEntity<List<Address>> findByUserId(HttpServletRequest request) {
        try {
            String authHeader = request.getHeader("Authorization");
            String jwt = authHeader.substring(7);

            return new ResponseEntity<>(service.findbyUser(jwt), HttpStatus.OK);
        } catch (NoSuchElementException nsee) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    @ApiOperation("Obtém um endereço pelo seu ID")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Endereço encontrado com sucesso"),
            @ApiResponse(code = 401, message = "Não autorizado"),
            @ApiResponse(code = 404, message = "Endereço não encontrado"),
            @ApiResponse(code = 500, message = "Erro interno do servidor")
    })
    public ResponseEntity<Address> findById(@PathVariable("id") Long id) {
        try {
            return new ResponseEntity<>(service.findById(id), HttpStatus.OK);
        } catch (NoSuchElementException nsee) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
