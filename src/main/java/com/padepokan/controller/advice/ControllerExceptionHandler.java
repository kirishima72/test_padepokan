package com.padepokan.controller.advice;

import com.padepokan.exception.NasabahNotFoundException;
import com.padepokan.dto.DtoResponse;
import com.padepokan.exception.TransactionIllegalPymentTypeException;
import com.padepokan.exception.TransactionValidationException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *
 * @author reza.mr
 */
@RestControllerAdvice
public class ControllerExceptionHandler {
    
    @ExceptionHandler({NasabahNotFoundException.class})
    DtoResponse controllerExceptionHandler(NasabahNotFoundException ex) {
        DtoResponse response = new DtoResponse();
        response.setStatus("0");
        response.setMessage(ex.getMessage());
        return response;
    }
    
    @ExceptionHandler(TransactionIllegalPymentTypeException.class)
    DtoResponse transactionIllegalPymentTypeException(TransactionIllegalPymentTypeException ex) {
        DtoResponse response = new DtoResponse();
        response.setStatus("0");
        response.setMessage(ex.getMessage());
        return response;
    }
    
    @ExceptionHandler(TransactionValidationException.class)
    DtoResponse transactionValidationException(TransactionValidationException ex) {
        DtoResponse response = new DtoResponse();
        response.setStatus("0");
        response.setMessage(ex.getMessage());
        response.setErrorList(ex.getListError());
        return response;
    }
    
}
