package com.app.aop.domain.business;

import com.app.aop.domain.config.MessageLoader;
import com.app.aop.domain.dto.CustomerDto;
import com.app.aop.domain.dto.ResponseDto;
import com.app.aop.domain.exception.BusinessRuleException;
import com.app.aop.domain.mapper.ICustomerMapper;
import com.app.aop.domain.model.Customer;
import com.app.aop.domain.repository.ICustomerRepository;
import com.app.aop.util.MessagesConstants;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor(onConstructor = @__ (@Autowired))
public class CustomerBusinessImpl implements ICustomerBusiness{

    private final ICustomerRepository customerRepository;
    private final ICustomerMapper customerMapper;

    @Override
    public ResponseDto<CustomerDto> getByDocumentNumber(String documentNumber) {
        if (StringUtils.isEmpty(documentNumber)) throw new BusinessRuleException(HttpStatus.BAD_REQUEST.value(), MessagesConstants.EM002, MessageLoader.getInstance().getMessage(MessagesConstants.EM002, "documentNumber"));
        Customer customer = this.customerRepository.findByDocumentNumber(documentNumber).orElseThrow(()->new BusinessRuleException(HttpStatus.BAD_REQUEST.value(), MessagesConstants.EM001, MessageLoader.getInstance().getMessage(MessagesConstants.EM001, documentNumber)));
        return new ResponseDto<>(HttpStatus.OK.value(),MessageLoader.getInstance().getMessage(MessagesConstants.IM001, documentNumber), this.customerMapper.toDto(customer));
    }
}
