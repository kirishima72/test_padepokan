package com.padepokan.service;

import com.padepokan.dto.DtoReportPrintRekening;
import com.padepokan.dto.DtoResponse;
import java.text.ParseException;

/**
 *
 * @author reza.mr
 */
public interface ReportPrintRekeningService {

    DtoResponse getReport(DtoReportPrintRekening req) throws ParseException;

}
