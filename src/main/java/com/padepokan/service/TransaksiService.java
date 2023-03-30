package com.padepokan.service;

import com.padepokan.dto.DtoResponse;
import com.padepokan.dto.DtoTransaksi;
import com.padepokan.exception.NasabahNotFoundException;
import com.padepokan.exception.TransactionValidationException;
import java.text.ParseException;

/**
 *
 * @author reza.mr
 */
public interface TransaksiService {
    
    DtoResponse createTransaction(DtoTransaksi req) 
            throws InterruptedException, NasabahNotFoundException, 
                    ParseException, TransactionValidationException;
    
}
