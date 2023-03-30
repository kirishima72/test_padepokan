package com.padepokan.service.impl;

import com.padepokan.constant.CommonConstant;
import com.padepokan.dao.NasabahDao;
import com.padepokan.dao.ReportPrintRekeningDao;
import com.padepokan.dao.TransaksiDao;
import com.padepokan.dto.DtoReportPrintRekening;
import com.padepokan.dto.DtoReportTransaksi;
import com.padepokan.dto.DtoResponse;
import com.padepokan.model.Nasabah;
import com.padepokan.model.ReportPrintRekening;
import com.padepokan.model.Transaksi;
import com.padepokan.service.ReportPrintRekeningService;
import jakarta.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author reza.mr
 */
@Service
@Transactional
public class ReportPrintRekeningServiceImpl implements ReportPrintRekeningService {

    @Autowired
    private TransaksiDao transaksiDao;

    @Autowired
    private ReportPrintRekeningDao reportPrintRekeningDao;

    @Autowired
    private NasabahDao nasabahDao;

    @Override
    public DtoResponse getReport(DtoReportPrintRekening req) throws ParseException {

        DtoResponse response = new DtoResponse();
        Optional<Nasabah> dataNasabah = nasabahDao.findById(req.getAccountId());

        if (!dataNasabah.isPresent()) {
            response.setStatus("0");
            response.setMessage("User not found");
            return response;
        }

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Date startDate = sdf.parse(req.getStartDate());
        Date endDate = sdf.parse(req.getEndDate());

        List<Transaksi> listTransaksi = transaksiDao.getAllBetweenDates(startDate, endDate, dataNasabah.get().getAccountId());

        if (listTransaksi.isEmpty()) {
            response.setStatus("0");
            response.setMessage("Failed");
            return response;
        }

        List data = listTransaksi.stream().map(trx -> {
            DtoReportTransaksi _trx = new DtoReportTransaksi();
            _trx.setTransactionDate(trx.getTransactionDate());
            _trx.setDescription(trx.getDescription());
            _trx.setAmount(trx.getAmount());
            
            if (trx.getStatus().equalsIgnoreCase(CommonConstant.CREDIT)) {
                _trx.setCredit(trx.getAmount());
            } else {
                _trx.setDebit(trx.getAmount());
            }
            
           return _trx;
        }).collect(Collectors.toList());
        
        
        ReportPrintRekening report = new ReportPrintRekening();
        report.setNasabah(dataNasabah.get());
        report.setStartDate(startDate);
        report.setEndDate(endDate);
        reportPrintRekeningDao.save(report);

        response.setStatus("1");
        response.setMessage("Success");
        response.setData(data);
        return response;
    }

}
