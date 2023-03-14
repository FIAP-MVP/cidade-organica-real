package br.com.fiap.cidade.repository;

import br.com.fiap.cidade.model.Adress;
import br.com.fiap.cidade.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface AdressRepository extends JpaRepository<Adress, Integer> {

    @Query(value = "SELECT * FROM address WHERE user_id=?1",  nativeQuery = true)
    List<Adress> findByUserId(@Param("user_id") Long userId);

}
