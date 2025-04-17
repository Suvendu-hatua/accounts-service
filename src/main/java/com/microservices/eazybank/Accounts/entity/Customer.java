package com.microservices.eazybank.Accounts.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "customer")
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Customer extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "customer_id")
    private long customerId;
    private String name;
    private String email;

    @Column(name = "mobile_number")
    private String mobileNumber;
}
