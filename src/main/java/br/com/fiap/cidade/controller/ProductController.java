package br.com.fiap.cidade.controller;

import br.com.fiap.cidade.dto.ProductDTO;
import br.com.fiap.cidade.model.Product;
import br.com.fiap.cidade.model.User;
import br.com.fiap.cidade.service.ProductService;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;


    @GetMapping(path="/mock",  produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Object getMockProducts() throws IOException {
        FileInputStream fis = new FileInputStream("src/main/java/br/com/fiap/cidade/utils/productMock.json");
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap> dataAsMap = mapper.readValue(fis, List.class);
        return dataAsMap;
    }

    @GetMapping("/{store}/{product}")
    public ResponseEntity<Product> findById(HttpServletRequest request, @PathVariable("store") Long storeId, @PathVariable("product") Long productId) {
        try {
            return new ResponseEntity<>(productService.findById(productId), HttpStatus.OK);
        }catch(NoSuchElementException nsee){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/all")
    public ResponseEntity<List<Product>> getAllProducts(HttpServletRequest request) {
        try {
            return new ResponseEntity<>(productService.getAllProducts(), HttpStatus.OK);
        }catch(NoSuchElementException nsee){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/{store}")
    public ResponseEntity<Product> save(HttpServletRequest request,@PathVariable("store") Long storeId ,@RequestBody ProductDTO product) throws IOException {
        try {
            String authHeader = request.getHeader("Authorization");
            String jwt = authHeader.substring(7);
            return new ResponseEntity<>(productService.create(product,storeId,jwt), HttpStatus.OK);

        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/{store}/{product}")
    public ResponseEntity<Product> update(HttpServletRequest request,@PathVariable("store") Long storeId, @PathVariable("product") Long productId, @RequestBody ProductDTO newProduct) throws IOException {
        try{
            String authHeader = request.getHeader("Authorization");
            String jwt = authHeader.substring(7);
            Product product = new Product();
            product.setName(newProduct.getName());
            product.setDescription(newProduct.getDescription());
            product.setPrice(newProduct.getPrice());
            product.setStock(newProduct.getStock());
            return new ResponseEntity<>(productService.update(productId,product,storeId,jwt), HttpStatus.OK);
        }catch(NoSuchElementException nsee){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{store}/{product}")
    public ResponseEntity<HttpStatus> delete(HttpServletRequest request, @PathVariable("store") Long storeId, @PathVariable("product") Long productId) {
        try{
            String authHeader = request.getHeader("Authorization");
            String jwt = authHeader.substring(7);
            productService.delete(productId,storeId,jwt);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch(NoSuchElementException nsee){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @PatchMapping("/{store}/{product}/uploadImage")
    public ResponseEntity<Void> uploadImage(HttpServletRequest request, @PathVariable("store") Long storeId, @PathVariable("product") Long productId, @RequestParam("image") MultipartFile image) throws IOException {
        try {
            String authHeader = request.getHeader("Authorization");
            String jwt = authHeader.substring(7);
            String imageBase64 = Base64.getEncoder().encodeToString(image.getBytes());
            productService.uploadImage(imageBase64,productId,storeId,jwt);
            return ResponseEntity.ok().build();
        } catch (NoSuchElementException nsee) {
            return ResponseEntity.notFound().build();
        }
    }
}
