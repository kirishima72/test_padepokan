package com.padepokan.exception;

import com.padepokan.dto.DtoMessages;
import java.util.List;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author reza.mr
 */
@Getter @Setter
public class TransactionValidationException extends Exception{

    private List<DtoMessages> listError;
    
    public TransactionValidationException() {
    }

    public TransactionValidationException(String message) {
        super(message);
    }

    public TransactionValidationException(List<DtoMessages> listError) {
        this.listError = listError;
    }
    
}
