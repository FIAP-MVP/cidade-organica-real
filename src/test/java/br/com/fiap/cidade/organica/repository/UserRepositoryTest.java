package br.com.fiap.cidade.organica.repository;

import br.com.fiap.cidade.model.User;
import br.com.fiap.cidade.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;
import java.util.Optional;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("tests")
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    TestEntityManager testEntityManager;

    @Autowired
    UserRepository userRepository;

    @Test
    @Order(1)
    @Rollback(value = false)
    @DisplayName("Should be able to create a new user.")
    public void createNewUserTest(){
        User user = newUser();
        userRepository.save(user);
        Assertions.assertThat(user.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    @Rollback(value = false)
    @DisplayName("Should be able to get a user.")
    public void getUserByIdTest(){
        User user = userRepository.findById(Integer.valueOf("1L")).get();
        Assertions.assertThat(user.getId()).isEqualTo(1L);

    }

    @Test
    @Order(3)
    @Rollback(value = false)
    @DisplayName("Should be able to get all users.")
    public void getAllUsersTest(){
        List<User> users = userRepository.findAll();
        Assertions.assertThat(users.size()).isGreaterThan(0);

    }

    @Test
    @Order(4)
    @Rollback(value = false)
    @DisplayName("Should be able to update a user")
    public void updateUserTest(){

        User user = userRepository.findById(Integer.valueOf("1L")).get();

        user.setEmail("ted.mosby@gmail.com");

        User updatedUser =  userRepository.save(user);

        Assertions.assertThat(updatedUser.getEmail()).isEqualTo("ted.mosby@gmail.com");

    }

    @Test
    @Order(5)
    @Rollback(value = false)
    @DisplayName("Should be able to delete a user")
    public void deleteUserTest(){

        User user = userRepository.findById(Integer.valueOf("1L")).get();

        userRepository.delete(user);

        User user1 = null;

        Optional<User> optionalUser = userRepository.findByEmail("ted.mosby@gmail.com");

        if(optionalUser.isPresent()){
            user1 = optionalUser.get();
        }

        Assertions.assertThat(user1).isNull();
    }

    public static User newUser(){
        return br.com.fiap.cidade.model.User.builder()
                .name("Barney")
                .lastName("Stinson")
                .cpf("111.111.111-11")
                .email("legendary.barney@gmail.com")
                .phone("11 11234-5678")
                .password("c2VuaGExMjNA".getBytes())
                .build();
    }
}
