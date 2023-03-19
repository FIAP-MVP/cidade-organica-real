package br.com.fiap.cidade.controller;

import br.com.fiap.cidade.model.Product;
import br.com.fiap.cidade.model.User;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/products")
public class ProductController {
    @GetMapping(path="/mock",  produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Object getMockProducts() throws IOException {
        FileInputStream fis = new FileInputStream("src/main/java/br/com/fiap/cidade/utils/productMock.json");
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap> dataAsMap = mapper.readValue(fis, List.class);
        return dataAsMap;
    }
}
