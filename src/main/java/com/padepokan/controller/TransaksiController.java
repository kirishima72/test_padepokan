package com.padepokan.controller;

import com.padepokan.dto.DtoResponse;
import com.padepokan.dto.DtoTransaksi;
import com.padepokan.exception.NasabahNotFoundException;
import com.padepokan.exception.TransactionIllegalPymentTypeException;
import com.padepokan.exception.TransactionValidationException;
import com.padepokan.service.TransaksiService;
import java.text.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author reza.mr
 */
@RestController
@RequestMapping("/api/transaction/")
public class TransaksiController {
    
    @Autowired
    private TransaksiService transaksiService;
    
    @PostMapping(
            value = "create", 
            consumes = MediaType.APPLICATION_JSON_VALUE, 
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public DtoResponse getReport(@RequestBody DtoTransaksi req) 
            throws ParseException, InterruptedException, 
                    TransactionValidationException, NasabahNotFoundException {
        return transaksiService.createTransaction(req);
    }
    
}
