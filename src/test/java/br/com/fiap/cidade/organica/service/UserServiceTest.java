package br.com.fiap.cidade.organica.service;

import br.com.fiap.cidade.model.User;
import br.com.fiap.cidade.repository.UserRepository;
import br.com.fiap.cidade.service.UserServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {
    @Mock
    private UserRepository repository;

    @InjectMocks
    private UserServiceImpl service;

    @Test
    void create() {
        User user = new User();
        when(repository.save(any(User.class))).thenReturn(user);
        User result = service.create(user);
        verify(repository, times(1)).save(user);
        assertEquals(user, result);
    }

    @Test
    void update() {
        User user = new User();
        when(repository.save(any(User.class))).thenReturn(user);
        User result = service.update(user);
        verify(repository, times(1)).save(user);
        assertEquals(user, result);
    }

    @Test
    void findById() {
        long id = 1;
        User user = new User();
        when(repository.findById(Math.toIntExact(id))).thenReturn(Optional.of(user));
        User result = service.findById(id);
        verify(repository, times(1)).findById(Math.toIntExact(id));
        assertEquals(user, result);
    }

    @Test
    void findAll() {
        service.findAll();
        verify(repository, times(1)).findAll();
    }

    @Test
    void delete() {
        long id = 1;
        service.delete(id);
        verify(repository, times(1)).deleteById(Math.toIntExact(id));
    }

    @Test
    void findByEmail() {
        String email = "test@email.com";
        User user = new User();
        when(repository.findByEmail(email)).thenReturn(Optional.of(user));
        Optional<User> result = service.findByEmail(email);
        verify(repository, times(1)).findByEmail(email);
        assertEquals(user, result.get());
    }

    public static User newUser(){
        return br.com.fiap.cidade.model.User.builder()
                .name("Barney")
                .lastName("Stinson")
                .cpf("111.111.111-11")
                .email("legendary.barney@gmail.com")
                .phone("11 11234-5678")
                .password("c2VuaGExMjNA")
                .build();
    }
}