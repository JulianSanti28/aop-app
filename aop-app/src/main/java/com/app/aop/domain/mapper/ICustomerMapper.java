package com.app.aop.domain.mapper;

import com.app.aop.domain.dto.CustomerDto;
import com.app.aop.domain.model.Customer;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ICustomerMapper extends IEntityMapper<CustomerDto, Customer>{

    Customer toDomain(CustomerDto dto);
    CustomerDto toDto(Customer entity);

}
