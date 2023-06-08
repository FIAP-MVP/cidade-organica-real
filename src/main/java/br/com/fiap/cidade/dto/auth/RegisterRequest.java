package br.com.fiap.cidade.dto.auth;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "RegisterRequest", description = "DTO para registro de usuário")
public class RegisterRequest {

    @ApiModelProperty(value = "Nome do usuário", example = "John")
    private String name;

    @ApiModelProperty(value = "Sobrenome do usuário", example = "Doe")
    private String lastName;

    @ApiModelProperty(value = "CPF do usuário", example = "123.456.789-00")
    private String cpf;

    @ApiModelProperty(value = "E-mail do usuário", example = "johndoe@example.com")
    private String email;

    @ApiModelProperty(value = "Telefone do usuário", example = "(11) 98765-4321")
    private String phone;

    @ApiModelProperty(value = "Senha do usuário", example = "MyPassword123")
    private String password;

}
