package br.com.fiap.cidade.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.time.LocalDateTime;




@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class OrderDTO {

    private String status;
    private Long custumerId;
    private Long addressId;
    private LocalDateTime creationDate;
    private LocalDateTime paymentDate;
    private BigDecimal totalValue;
    private BigDecimal shippingCost;
    private List<OrderProductDTO> products;

}
