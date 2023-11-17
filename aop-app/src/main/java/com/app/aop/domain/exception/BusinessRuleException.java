package com.app.aop.domain.exception;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString(callSuper = true)
public class BusinessRuleException extends BaseException {

    public BusinessRuleException(int status, String errorCode, String message) {
        super(status, errorCode, message);
    }
}
