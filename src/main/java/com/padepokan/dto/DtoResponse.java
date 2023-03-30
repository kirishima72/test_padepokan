package com.padepokan.dto;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 *
 * @author reza.mr
 */
@Data
@NoArgsConstructor
public class DtoResponse {
    
    private String status;
    
    private String message;
    
    private List<DtoMessages> errorList;
    
    private List<?> data;
    
    private int totalRecord;
    
}
