package br.com.fiap.cidade.service;

import br.com.fiap.cidade.model.User;
import br.com.fiap.cidade.repository.UserRepository;
import org.apache.catalina.connector.Request;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class AuthServiceImpl implements AuthService{
    private final UserRepository userRepository ;

    public AuthServiceImpl(UserRepository repository) {
        this.userRepository = repository;
    }

//    @Override
//    public Map<String, Object> execute(Request request) {
//
//        User user = userRepository.findOne(request.email);
//
//        if (user == null || !BCrypt.checkpw(request.password, user.password)) {
//            throw new AppError("Incorrect email/password combination", 401);
//        }
//
//        String token = JWT.create()
//                .withSubject(String.valueOf(user.id))
//                .withExpiresAt(new Date(System.currentTimeMillis() + AuthConfig.jwt.expiresIn * 1000))
//                .sign(Algorithm.HMAC256(AuthConfig.jwt.secret));
//
//        Map<String, Object> response = new HashMap<>();
//        response.put("user", user);
//        response.put("token", token);
//
//        return response;
//    }

}
