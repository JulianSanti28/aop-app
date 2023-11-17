package com.app.aop.domain.handler;

import com.app.aop.domain.config.MessageLoader;
import com.app.aop.domain.dto.ApiErrorDto;
import com.app.aop.domain.dto.ResponseDto;
import com.app.aop.domain.exception.BusinessRuleException;
import com.app.aop.util.MessagesConstants;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.List;

import static java.util.Objects.nonNull;

/**
 * Global Exception Handler to manage various exception types.
 */
@RestControllerAdvice
public class GlobalExceptionHandler {


    /**
     * Handles exceptions about type BusinessRuleException.
     * Logs the error message and returns a response for this specific exception.
     *
     * @param e The BusinessRuleException instance.
     * @return Response entity containing error details.
     */
    @ExceptionHandler(BusinessRuleException.class)
    public ResponseEntity<ResponseDto<Object>> handleBusinessRuleException(BusinessRuleException e) {
        return new ResponseDto<>(e.getStatus(), e.getMessage(), e.getErrorCode()).of();
    }

    /**
     * Handles MethodArgumentNotValidException.
     * Logs the error and returns a response entity with error details.
     *
     * @param ex The MethodArgumentNotValidException instance.
     * @return Response entity containing error details.
     */
    @ExceptionHandler
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto<List<ApiErrorDto>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        List<ApiErrorDto> result = ex.getBindingResult().getAllErrors()
                .stream()
                .map(this::mapError)
                .toList();
        return new ResponseDto<>(HttpStatus.BAD_REQUEST.value(), MessageLoader.getInstance().getMessage(MessagesConstants.EM002), result);
    }

    /**
     * Handles MissingServletRequestParameterException.
     * Logs the error and returns a response entity with error details.
     *
     * @param ex The MissingServletRequestParameterException instance.
     * @return Response entity containing error details.
     */
    @ExceptionHandler(MissingServletRequestParameterException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseDto<Void> handleMissingServletRequestParameterException(MissingServletRequestParameterException ex) {
        return new ResponseDto<>(HttpStatus.BAD_REQUEST.value(), MessageLoader.getInstance().getMessage(MessagesConstants.EM004, ex.getParameterName()));
    }

    /**
     * Handles RuntimeException.
     * Logs the error and returns a response entity with error details.
     *
     * @param ex The RuntimeException instance.
     * @return Response entity containing error details.
     */
    @ExceptionHandler(RuntimeException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseDto<Void> handleRuntimeException(RuntimeException ex) {
        return new ResponseDto<>(HttpStatus.INTERNAL_SERVER_ERROR.value(), MessageLoader.getInstance().getMessage(MessagesConstants.EM003));
    }

    /**
     * Maps ObjectError to ApiErrorDto.
     * Formats the error messages and creates an ApiErrorDto.
     *
     * @param objectError The ObjectError instance.
     * @return ApiErrorDto containing error details.
     */
    private ApiErrorDto mapError(ObjectError objectError) {
        if (objectError instanceof FieldError field) {
            return new ApiErrorDto(field.getField(),
                    MessageLoader.getInstance().getMessage(objectError.getDefaultMessage(), field.getField(), nonNull(field.getArguments()) && field.getArguments().length >= 2 ? field.getArguments()[1] : null));
        }
        return new ApiErrorDto(objectError.getObjectName(), MessageLoader.getInstance().getMessage(MessagesConstants.EM003));
    }
}


