package com.alexander.fullday.entity;
import com.alexander.fullday.enums.PaymentMethod;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "tb_payment")
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "payment_date", nullable = false)
    private LocalDate paymentDate;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private PaymentMethod paymentMethod;

    @Column(name = "operation_number", nullable = false, length = 100)
    private String operationNumber;

    @Column(name = "receipt_url", length = 500)
    private String receiptUrl; // URL del archivo almacenado en S3

    @OneToOne(optional = false, fetch = FetchType.LAZY)
    private Registration registration;
}
