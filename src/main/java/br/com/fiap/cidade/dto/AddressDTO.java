package br.com.fiap.cidade.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddressDTO {
    private String cep;
    private Integer number;
    private String complement;
    private String city;
    private String street;
    private String identifier;
}
