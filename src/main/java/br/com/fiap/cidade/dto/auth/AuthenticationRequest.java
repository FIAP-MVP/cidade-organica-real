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
@ApiModel(value = "AuthenticationRequest", description = "DTO para solicitação de autenticação")
public class AuthenticationRequest {

    @ApiModelProperty(value = "E-mail do usuário para autenticação", example = "example@example.com")
    private String email;

    @ApiModelProperty(value = "Senha do usuário para autenticação", example = "mypassword")
    private String password;
}
