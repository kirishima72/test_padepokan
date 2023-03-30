package com.padepokan.dto;

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
public class DtoPoint {
    
    private long accountId;
    
    private String name;
    
    private long totalPoint;
    
}
