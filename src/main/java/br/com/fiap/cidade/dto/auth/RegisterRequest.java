package br.com.fiap.cidade.dto.auth;

import br.com.fiap.cidade.model.Adress;
import jakarta.persistence.Column;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequest {

    private String name;
    private String lastName;
    private String cpf;
    private String email;
    private String phone;
    private List<Adress> adresses;
    private String image;
    private String password;


}
