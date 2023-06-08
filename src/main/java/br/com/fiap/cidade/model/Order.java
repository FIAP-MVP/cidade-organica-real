package br.com.fiap.cidade.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "order")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToMany(mappedBy = "order")
    private List<OrderProduct> orderProducts;
    public enum Status {
        ABERTO, PAGO, PROCESSANDO, ENVIADO, ENTREGUE
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private Status status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id")
    private User customer;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "delivery_address_id")
    private Address deliveryAddress;

    @Column(name = "creation_date")
    private LocalDateTime creationDate;

    @Column(name = "payment_date")
    private LocalDateTime paymentDate;

    @Column(name = "total_value")
    private BigDecimal totalValue;

    @Column(name = "shipping_cost")
    private BigDecimal shippingCost;

    //todo Ver como vai ser feito o pagamento
    //@OneToOne(cascade = CascadeType.ALL)
    //    @JoinColumn(name = "payment_info_id", referencedColumnName = "id")
    //    private PaymentInfo paymentInfo;
}
