package com.app.aop.domain.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Address {
    @Id
    private Long addressId;
    @Column(length = 60)
    private String description;
    @Column(length = 60)
    private String city;
    @OneToOne @MapsId @JoinColumn(name = "customer_id",insertable = false, updatable = false)
    private Customer customer;
}
