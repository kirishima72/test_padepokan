package com.padepokan.controller;

import com.padepokan.dto.DtoReportPrintRekening;
import com.padepokan.dto.DtoResponse;
import com.padepokan.service.ReportPrintRekeningService;
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
@RequestMapping("/api/report/")
public class ReportPrintRekeningController {
    
    @Autowired
    private ReportPrintRekeningService reportPrintRekeningService;
    
    @PostMapping(
            value = "print-report", 
            consumes = MediaType.APPLICATION_JSON_VALUE, 
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public DtoResponse getReport(@RequestBody DtoReportPrintRekening req) throws ParseException {
        return reportPrintRekeningService.getReport(req);
    }
    
}
