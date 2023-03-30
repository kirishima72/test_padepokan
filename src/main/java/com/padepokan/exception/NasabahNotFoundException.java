package com.padepokan.exception;

import lombok.EqualsAndHashCode;

/**
 *
 * @author reza.mr
 */
public class NasabahNotFoundException extends Exception{

    public NasabahNotFoundException() {
    }
    
    public NasabahNotFoundException(String message) {
        super(message);
    }
    
}
