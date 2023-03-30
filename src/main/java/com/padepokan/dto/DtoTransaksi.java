package com.padepokan.dto;

import java.math.BigDecimal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author reza.mr
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DtoTransaksi {
    
    private long accountId;
    
    private String transactionDate;
    
    private String description;
    
    private String paymentType;
    
    private BigDecimal amount;
    
}
