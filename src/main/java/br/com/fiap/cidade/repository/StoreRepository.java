package br.com.fiap.cidade.repository;

import br.com.fiap.cidade.model.Adress;
import br.com.fiap.cidade.model.Store;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StoreRepository extends JpaRepository<Store, Long> {
}
