package com.app.aop.domain.business;

import com.app.aop.domain.dto.CustomerDto;
import com.app.aop.domain.dto.ResponseDto;

/**
 * An interface for Customer Business operations.
 */
public interface ICustomerBusiness {

    /**
     * Retrieves a CustomerDto based on the provided document number.
     *
     * @param documentNumber The document number used to fetch the customer information.
     * @return A ResponseDto containing the CustomerDto information if found, or an appropriate response if not found.
     */
    ResponseDto<CustomerDto> getByDocumentNumber(final String documentNumber);
}

