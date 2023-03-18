//package br.com.fiap.cidade.organica.controller;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import br.com.fiap.cidade.controller.UserController;
//import br.com.fiap.cidade.model.User;
//import br.com.fiap.cidade.service.UserService;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.mock.web.MockMultipartFile;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Optional;
//
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class UserControllerTest {
//    @InjectMocks
//    private UserController controller;
//
//    @Mock
//    private UserService service;
//
////    @Test
////    void testCreate() {
////        User user = new User();
////        when(service.create(user)).thenReturn(user);
////        assertEquals(user, controller.create(user));
////    }
//
//    @Test
//    void testUpdate() {
//        User user = new User();
//        when(service.update(user)).thenReturn(user);
//        assertEquals(user, controller.update(user));
//    }
//
//    @Test
//    void testFindById() {
//        Long id = 1L;
//        User user = new User();
//        when(service.findById(id)).thenReturn(user);
//        assertEquals(user, controller.findById(id));
//    }
//
//    @Test
//    void testFindAll() {
//        List<User> users = new ArrayList<>();
//        when(service.findAll()).thenReturn(users);
//        assertEquals(users, controller.findAll());
//    }
//
//    @Test
//    void testDelete() {
//        Long id = 1L;
//        controller.delete(id);
//        verify(service, times(1)).delete(id);
//    }
//
//
////    @Test
////    void testUploadImage() throws IOException {
////        Long id = 1L;
////        byte[] imageBytes = "image".getBytes();
////        MultipartFile image = new MockMultipartFile("image", "image.png", "image/png", imageBytes);
////        User user = new User();
////        when(service.uploadImage(id, anyString())).thenReturn(user);
////        assertEquals(user, controller.uploadImage(image, id));
////    }
//
//    public static User newUser(){
//        return br.com.fiap.cidade.model.User.builder()
//                .name("Barney")
//                .lastName("Stinson")
//                .cpf("111.111.111-11")
//                .email("legendary.barney@gmail.com")
//                .phone("11 11234-5678")
//                .password("c2VuaGExMjNA")
//                .build();
//    }
//}
