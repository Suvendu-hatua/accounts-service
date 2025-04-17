package com.microservices.eazybank.Accounts.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

@Setter
@Getter
@ToString
@MappedSuperclass
public class BaseEntity {
    @Column(name = "created_at",updatable = false)
    private LocalDateTime createdAt;

    @Column(name = "created_by",updatable = false)
    private String createdBy;

    @Column(name = "updated_at",insertable = false)
    private LocalDateTime updatedAt;

    @Column(name = "updated_by",insertable = false)
    private String updatedBy;
}
