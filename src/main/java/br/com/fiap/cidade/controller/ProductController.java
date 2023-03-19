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

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {
    @GetMapping(path="/mock",  produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody Object getMockProducts() {
        ObjectMapper mapper = new ObjectMapper();
        return "[\n" +
                "  {\n" +
                "    \"id\": 1,\n" +
                "    \"name\": \"Mel orgânico\",\n" +
                "    \"description\": \"Mel produzido de maneira sustentável e sem agrotóxicos\",\n" +
                "    \"price\": 15.50,\n" +
                "    \"image\": \"https://example.com/images/mel_organico.jpg\",\n" +
                "    \"stock\": 100,\n" +
                "    \"categories\": [\n" +
                "      {\n" +
                "        \"id\": 1,\n" +
                "        \"name\": \"Produtos Naturais\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"store\": {\n" +
                "      \"id\": 1,\n" +
                "      \"name\": \"Mercado Verde\"\n" +
                "    }\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 2,\n" +
                "    \"name\": \"Geleia de frutas vermelhas\",\n" +
                "    \"description\": \"Geleia produzida com frutas orgânicas colhidas na agricultura familiar\",\n" +
                "    \"price\": 10.90,\n" +
                "    \"image\": \"https://example.com/images/geleia_familia.jpg\",\n" +
                "    \"stock\": 50,\n" +
                "    \"categories\": [\n" +
                "      {\n" +
                "        \"id\": 1,\n" +
                "        \"name\": \"Produtos Naturais\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"store\": {\n" +
                "      \"id\": 2,\n" +
                "      \"name\": \"Feira Agroecológica\"\n" +
                "    }\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 3,\n" +
                "    \"name\": \"Queijo artesanal\",\n" +
                "    \"description\": \"Queijo produzido de maneira artesanal com leite de vacas criadas soltas na pastagem\",\n" +
                "    \"price\": 22.00,\n" +
                "    \"image\": \"https://example.com/images/queijo_artesanal.jpg\",\n" +
                "    \"stock\": 30,\n" +
                "    \"categories\": [\n" +
                "      {\n" +
                "        \"id\": 2,\n" +
                "        \"name\": \"Agricultura Familiar\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"store\": {\n" +
                "      \"id\": 3,\n" +
                "      \"name\": \"Queijaria da Roça\"\n" +
                "    }\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 4,\n" +
                "    \"name\": \"Pão integral\",\n" +
                "    \"description\": \"Pão integral produzido com farinha orgânica e fermentação natural\",\n" +
                "    \"price\": 6.50,\n" +
                "    \"image\": \"https://example.com/images/pao_integral.jpg\",\n" +
                "    \"stock\": 80,\n" +
                "    \"categories\": [\n" +
                "      {\n" +
                "        \"id\": 1,\n" +
                "        \"name\": \"Produtos Naturais\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"store\": {\n" +
                "      \"id\": 4,\n" +
                "      \"name\": \"Padaria Artesanal\"\n" +
                "    }\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 5,\n" +
                "    \"name\": \"Tomate orgânico\",\n" +
                "    \"description\": \"Tomate produzido de maneira sustentável e sem agrotóxicos\",\n" +
                "    \"price\": 3.20,\n" +
                "    \"image\": \"https://example.com/images/tomate_organico.jpg\",\n" +
                "    \"stock\": 120,\n" +
                "    \"categories\": [\n" +
                "      {\n" +
                "        \"id\": 2,\n" +
                "        \"name\": \"Agricultura Familiar\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"store\": {\n" +
                "      \"id\": 5,\n" +
                "      \"name\": \"Hortifruti da Roça\"\n" +
                "    }\n" +
                "  },\n" +
                "  {    \"id\": 16,    \"name\": \"Queijo minas artesanal\",    \"description\": \"Queijo minas artesanal produzido por pequenos produtores da região.\",    \"price\": 25.0,    \"image\": \"https://example.com/queijo-minas.jpg\",    \"stock\": 10,    \"categories\": [      {        \"id\": 3,        \"name\": \"Queijos\"      },      {        \"id\": 4,        \"name\": \"Produtos artesanais\"      }    ],\n" +
                "    \"store\": {\n" +
                "      \"id\": 2,\n" +
                "      \"name\": \"Empório da Roça\",\n" +
                "      \"address\": \"Rua das Flores, 123\",\n" +
                "      \"city\": \"São Paulo\",\n" +
                "      \"state\": \"SP\"\n" +
                "    }\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 6,\n" +
                "    \"name\": \"Geleia de frutas vermelhas\",\n" +
                "    \"description\": \"Geleia de frutas vermelhas feita com frutas frescas e sem adição de conservantes.\",\n" +
                "    \"price\": 15.0,\n" +
                "    \"image\": \"https://example.com/geleia-frutas-vermelhas.jpg\",\n" +
                "    \"stock\": 20,\n" +
                "    \"categories\": [\n" +
                "      {\n" +
                "        \"id\": 5,\n" +
                "        \"name\": \"Geleias\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 6,\n" +
                "        \"name\": \"Produtos naturais\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"store\": {\n" +
                "      \"id\": 3,\n" +
                "      \"name\": \"Sítio das Frutas\",\n" +
                "      \"address\": \"Estrada da Roça, s/n\",\n" +
                "      \"city\": \"Belo Horizonte\",\n" +
                "      \"state\": \"MG\"\n" +
                "    }\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 7,\n" +
                "    \"name\": \"Tomate Orgânico\",\n" +
                "    \"description\": \"Tomate orgânico cultivado sem agrotóxicos.\",\n" +
                "    \"price\": 5.99,\n" +
                "    \"image\": \"https://images.unsplash.com/photo-1577652468679-b9b022cecb79\",\n" +
                "    \"stock\": 100,\n" +
                "    \"categories\": [\n" +
                "      {\n" +
                "        \"id\": 1,\n" +
                "        \"name\": \"Frutas e Verduras\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 3,\n" +
                "        \"name\": \"Orgânicos\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"store\": {\n" +
                "      \"id\": 1,\n" +
                "      \"name\": \"Feira da Agricultura Familiar\",\n" +
                "      \"address\": \"Rua da Agricultura, 123\",\n" +
                "      \"city\": \"São Paulo\",\n" +
                "      \"state\": \"SP\"\n" +
                "    }\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 8,\n" +
                "    \"name\": \"Café Orgânico\",\n" +
                "    \"description\": \"Café orgânico produzido em pequenas propriedades familiares.\",\n" +
                "    \"price\": 14.99,\n" +
                "    \"image\": \"https://images.unsplash.com/photo-1531973576160-5a810fda9663\",\n" +
                "    \"stock\": 80,\n" +
                "    \"categories\": [\n" +
                "      {\n" +
                "        \"id\": 5,\n" +
                "        \"name\": \"Cafés\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 3,\n" +
                "        \"name\": \"Orgânicos\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"store\": {\n" +
                "      \"id\": 3,\n" +
                "      \"name\": \"Associação dos Agricultores Familiares\",\n" +
                "      \"address\": \"Rua da Agricultura, 789\",\n" +
                "      \"city\": \"Curitiba\",\n" +
                "      \"state\": \"PR\"\n" +
                "    }\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 9,\n" +
                "    \"name\": \"Alface Americana\",\n" +
                "    \"description\": \"Alface americana cultivada de forma orgânica em agricultura familiar\",\n" +
                "    \"price\": 2.99,\n" +
                "    \"image\": \"https://cdn.pixabay.com/photo/2021/09/14/15/07/lettuce-6626015_960_720.jpg\",\n" +
                "    \"stock\": 50,\n" +
                "    \"categories\": [\n" +
                "      {\n" +
                "        \"id\": 1,\n" +
                "        \"name\": \"Verduras e Legumes\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 2,\n" +
                "        \"name\": \"Produtos Orgânicos\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"store\": {\n" +
                "      \"id\": 1,\n" +
                "      \"name\": \"Feira da Agricultura Familiar\",\n" +
                "      \"address\": \"Rua das Flores, 123\",\n" +
                "      \"phone\": \"+55 (11) 1234-5678\"\n" +
                "    }\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 10,\n" +
                "    \"name\": \"Brócolis\",\n" +
                "    \"description\": \"Brócolis cultivado de forma orgânica em agricultura familiar\",\n" +
                "    \"price\": 4.99,\n" +
                "    \"image\": \"https://cdn.pixabay.com/photo/2017/08/08/08/15/broccoli-2610967_960_720.jpg\",\n" +
                "    \"stock\": 30,\n" +
                "    \"categories\": [\n" +
                "      {\n" +
                "        \"id\": 1,\n" +
                "        \"name\": \"Verduras e Legumes\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 2,\n" +
                "        \"name\": \"Produtos Orgânicos\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"store\": {\n" +
                "      \"id\": 1,\n" +
                "      \"name\": \"Feira da Agricultura Familiar\",\n" +
                "      \"address\": \"Rua das Flores, 123\",\n" +
                "      \"phone\": \"+55 (11) 1234-5678\"\n" +
                "    }\n" +
                "  },\n" +
                "  {\n" +
                "    \"id\": 11,\n" +
                "    \"name\": \"Maçã Gala\",\n" +
                "    \"description\": \"Maçãs gala cultivadas de forma orgânica em agricultura familiar\",\n" +
                "    \"price\": 6.99,\n" +
                "    \"image\": \"https://cdn.pixabay.com/photo/2016/11/22/18/52/apple-1851464_960_720.jpg\",\n" +
                "    \"stock\": 20,\n" +
                "    \"categories\": [\n" +
                "      {\n" +
                "        \"id\": 3,\n" +
                "        \"name\": \"Frutas\"\n" +
                "      },\n" +
                "      {\n" +
                "        \"id\": 2,\n" +
                "        \"name\": \"Produtos Orgânicos\"\n" +
                "      }\n" +
                "    ],\n" +
                "    \"store\": {\n" +
                "      \"id\": 2,\n" +
                "      \"name\": \"Mercado da Agricultura Familiar\",\n" +
                "      \"address\": \"Avenida das Árvores, 456\",\n" +
                "      \"phone\": \"+55 (11) 9876-5432\"\n" +
                "    }\n" +
                "  }\n" +
                "]";
    }
}
