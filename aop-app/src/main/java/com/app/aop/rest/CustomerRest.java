package com.app.aop.rest;

import com.app.aop.domain.business.ICustomerBusiness;
import com.app.aop.domain.dto.CustomerDto;
import com.app.aop.domain.dto.ResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customer")
@RequiredArgsConstructor(onConstructor = @__ (@Autowired))
public class CustomerRest {

    private final ICustomerBusiness customerBusiness;

    @GetMapping
    public ResponseEntity<ResponseDto<CustomerDto>> getCustomerByDocumentNumber(@RequestParam final String documentNumber){
        return this.customerBusiness.getByDocumentNumber(documentNumber).of();
    }

}
