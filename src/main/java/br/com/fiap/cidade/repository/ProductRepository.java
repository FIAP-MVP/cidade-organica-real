package br.com.fiap.cidade.repository;

import br.com.fiap.cidade.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {
}
