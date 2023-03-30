package com.padepokan.dao;

import com.padepokan.model.Nasabah;
import com.padepokan.model.ReportPrintRekening;
import com.padepokan.model.Transaksi;
import jakarta.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author reza.mr
 */
@SpringBootTest
public class ReportPrintRekeningTest {

       
    @Autowired
    private TransaksiDao transaksiDao;
    
    @Autowired
    private ReportPrintRekeningDao reportPrintRekeningDao;
    
    @Autowired
    private NasabahDao nasabahDao;
    
//    @Test
//    @Transactional()
    public void printReportTest() throws ParseException {
        
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date startDate = sdf.parse("26-03-2023");
        Date endDate = sdf.parse("30-03-2023");
        List<Transaksi> listTransaksi = transaksiDao.getAllBetweenDates(startDate, endDate, 1L);
        
        Assertions.assertNotNull(listTransaksi);
        
        Nasabah nasabah = nasabahDao.findById(1L).get();
        
        ReportPrintRekening report = new ReportPrintRekening();
        report.setNasabah(nasabah);
        report.setStartDate(startDate);
        report.setEndDate(endDate);
        reportPrintRekeningDao.save(report);
        
    }
    
}
