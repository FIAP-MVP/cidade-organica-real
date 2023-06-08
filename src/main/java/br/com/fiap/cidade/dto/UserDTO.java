package br.com.fiap.cidade.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "UserDTO", description = "DTO para usuário")
public class UserDTO {

    @ApiModelProperty(value = "Nome do usuário", example = "John")
    private String name;

    @ApiModelProperty(value = "Sobrenome do usuário", example = "Doe")
    private String lastName;

    @ApiModelProperty(value = "E-mail do usuário", example = "johndoe@example.com")
    private String email;

    @ApiModelProperty(value = "Telefone do usuário", example = "+1 1234567890")
    private String phone;

    @ApiModelProperty(value = "Senha do usuário", example = "mypassword")
    private String password;
}
