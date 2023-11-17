package com.app.aop.domain.dto;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CustomerDto {

    @JsonIgnore
    private Long customerId;
    private String firstName;
    private String secondName;
    private String firstLastName;
    private String secondLastName;
    private Long phoneNumber;
    private AddressDto address;
}
