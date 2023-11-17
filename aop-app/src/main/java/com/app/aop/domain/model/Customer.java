package com.app.aop.domain.model;


import com.app.aop.domain.enums.DocumentTypeEnum;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long customerId;
    @Enumerated(EnumType.STRING)
    @Column(name = "document_type")
    private DocumentTypeEnum documentTypeEnum;
    @Column(length = 50)
    private String documentNumber;
    @Column(length = 100)
    private String firstName;
    @Column(length = 100)
    private String secondName;
    @Column(length = 100)
    private String firstLastName;
    @Column(length = 100)
    private String secondLastName;
    @Column(length = 35)
    private Long phoneNumber;
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL, optional = false, mappedBy = "customer")
    private Address address;
}
