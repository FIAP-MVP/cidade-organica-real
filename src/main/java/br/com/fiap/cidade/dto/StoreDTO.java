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
@ApiModel(value = "StoreDTO", description = "DTO para loja")
public class StoreDTO {

    @ApiModelProperty(value = "Nome da loja", example = "Minha Loja")
    private String name;

    @ApiModelProperty(value = "CNPJ da loja", example = "12.345.678/0001-90")
    private String cnpj;

    @ApiModelProperty(value = "Descrição da loja", example = "Uma loja online de produtos")
    private String description;
}
