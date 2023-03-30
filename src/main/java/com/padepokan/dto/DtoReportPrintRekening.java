package com.padepokan.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 *
 * @author reza.mr
 */
@Data
@AllArgsConstructor()
public class DtoReportPrintRekening {
    
    private long accountId;
    
    private String startDate;
    
    private String endDate;
    
}
