package com.microservices.eazybank.Accounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Accounts extends BaseEntity {
        @Column(name = "customer_id")
        private String customerId;

        @Column(name = "account_type")
        private String accountType;

        @Column(name = "account_number")
        @Id
        private String accountNumber;

        @Column(name = "branch_address")
        private String branchAddress;
}
