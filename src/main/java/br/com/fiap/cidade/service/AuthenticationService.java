package br.com.fiap.cidade.service;

import br.com.fiap.cidade.dto.auth.AuthenticationRequest;
import br.com.fiap.cidade.dto.auth.AuthenticationResponse;
import br.com.fiap.cidade.dto.auth.RegisterRequest;
import io.jsonwebtoken.Jwts;
import br.com.fiap.cidade.model.Role;
import br.com.fiap.cidade.model.User;
import br.com.fiap.cidade.repository.UserRepository;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository repository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;
    public AuthenticationResponse register(RegisterRequest request) {
        validateUser(request);
        var user = User.builder()
                .name(request.getName())
                .lastName(request.getLastName())
                .cpf(request.getCpf())
                .email(request.getEmail())
                .phone(request.getPhone())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        repository.save(user);
        Claims claims = Jwts.claims();
        claims.put("userId", user.getId());
        var jwtToken = jwtService.generateToken(claims,user);
        return AuthenticationResponse.builder()
                .name(request.getName())
                .token(jwtToken)
                .build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getEmail(),
                        request.getPassword()
                )
        );
        var user = repository.findByEmail(request.getEmail()).orElseThrow();
        Claims claims = Jwts.claims();
        claims.put("userId", user.getId());
        var jwtToken = jwtService.generateToken(claims,user);
        return AuthenticationResponse.builder()
                .name(user.getName())
                .token(jwtToken)
                .build();
    }

    private void validateUser(RegisterRequest user){
        if(repository.findByEmail(user.getEmail()).isPresent()){
            throw new IllegalArgumentException("Email já cadastrado");
        }
        if(repository.findByCpf(user.getCpf()).isPresent()){
            throw new IllegalArgumentException("Cpf já cadastrado");
        }
    }
}
