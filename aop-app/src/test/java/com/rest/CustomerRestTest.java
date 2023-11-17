package com.rest;

import com.app.aop.domain.business.CustomerBusinessImpl;
import com.app.aop.domain.config.MessageLoader;
import com.app.aop.domain.dto.CustomerDto;
import com.app.aop.domain.dto.ResponseDto;
import com.app.aop.rest.CustomerRest;
import com.app.aop.util.MessagesConstants;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.Mockito.when;

@ActiveProfiles("test")
@ExtendWith(MockitoExtension.class)
class CustomerRestTest {
    private static final String ENDPOINT = "/customer";

    @Mock
    CustomerBusinessImpl customerBusiness;
    @InjectMocks
    private CustomerRest customerRest;
    private MockMvc mockMvc;

    @BeforeEach
    public void setup() {
        this.customerRest = new CustomerRest(customerBusiness);
        mockMvc = MockMvcBuilders.standaloneSetup(customerRest)
                .build();
    }

    @Test
    void getCustomerByDocumentTest() throws Exception {
        final var DOCUMENT = "1";
        ResponseDto<CustomerDto> response = new ResponseDto<>(HttpStatus.OK.value(), MessageLoader.getInstance().getMessage(MessagesConstants.EM001));
        when(this.customerBusiness.getByDocumentNumber(DOCUMENT)).thenReturn(response);
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders
                .get(ENDPOINT)
                .param("documentNumber", DOCUMENT);
        mockMvc.perform(requestBuilder)
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andReturn();
    }

}

