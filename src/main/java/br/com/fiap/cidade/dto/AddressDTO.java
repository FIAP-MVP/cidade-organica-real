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
@ApiModel(value = "AddressDTO", description = "DTO para endereço")
public class AddressDTO {

    @ApiModelProperty(value = "CEP do endereço", example = "12345-678")
    private String cep;

    @ApiModelProperty(value = "Número do endereço", example = "123")
    private Integer number;

    @ApiModelProperty(value = "Complemento do endereço", example = "Apto 101")
    private String complement;

    @ApiModelProperty(value = "Cidade do endereço", example = "São Paulo")
    private String city;

    @ApiModelProperty(value = "Rua do endereço", example = "Rua Example")
    private String street;

    @ApiModelProperty(value = "Identificador do endereço", example = "Casa")
    private String identifier;

}
