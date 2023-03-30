package com.padepokan.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.math.BigDecimal;
import java.util.Date;
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
public class DtoReportTransaksi {
    
    @JsonFormat(pattern="yyyy-MM-dd")
    private Date transactionDate;
    
    private String description;
    
    private BigDecimal credit;
    
    private BigDecimal debit;
    
    private BigDecimal amount;
    
}
