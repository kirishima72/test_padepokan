package com.padepokan.service.impl;

import com.padepokan.constant.CommonConstant;
import com.padepokan.dao.NasabahDao;
import com.padepokan.dao.PointDao;
import com.padepokan.dao.TransaksiDao;
import com.padepokan.dto.DtoMessages;
import com.padepokan.dto.DtoResponse;
import com.padepokan.dto.DtoTransaksi;
import com.padepokan.exception.NasabahNotFoundException;
import com.padepokan.exception.TransactionIllegalPymentTypeException;
import com.padepokan.exception.TransactionValidationException;
import com.padepokan.helper.Helper;
import com.padepokan.model.Nasabah;
import com.padepokan.model.Point;
import com.padepokan.model.Transaksi;
import jakarta.transaction.Transactional;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.padepokan.service.TransaksiService;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author reza.mr
 */
@Service
@Transactional
public class TransaksiServiceImpl implements TransaksiService {

    @Autowired
    private TransaksiDao transaksiDao;

    @Autowired
    private PointDao pointDao;

    @Autowired
    private NasabahDao nasabahDao;

    @Override
    public DtoResponse createTransaction(DtoTransaksi req)
            throws InterruptedException, NasabahNotFoundException,
            ParseException, TransactionValidationException {

        validateRequestTransaction(req);

        Nasabah nasabah = nasabahDao.findById(req.getAccountId())
                .orElseThrow(() -> new NasabahNotFoundException("Nasabah not found."));

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date transactionDate = sdf.parse(req.getTransactionDate());

        Transaksi transaksi = new Transaksi();
        transaksi.setNasabah(nasabah);
        transaksi.setAmount(req.getAmount());
        transaksi.setDescription(req.getDescription().toUpperCase());
        transaksi.setStatus(req.getPaymentType());
        transaksi.setTransactionDate(transactionDate);
        transaksi = transaksiDao.save(transaksi);

        long gainPoint = 0;
        if (Helper.isGainPoint(transaksi.getDescription())) {
            gainPoint = Helper.calcPoint(transaksi.getAmount().doubleValue());
        }

        Point point = new Point();
        point.setNasabah(nasabah);
        point.setPoint(gainPoint);

        Optional<Point> dataPoint = pointDao.findById(nasabah.getAccountId());
        if (dataPoint.isPresent()) {
            point.setPoint(gainPoint + dataPoint.get().getPoint());
        }

        pointDao.save(point);

        DtoResponse response = new DtoResponse();
        response.setStatus("1");
        response.setMessage("Success");
        return response;
    }

    private static void validateRequestTransaction(DtoTransaksi req) throws TransactionValidationException {

        List<DtoMessages> listError = new ArrayList<>();

        if (req.getDescription() == null) {
            listError.add(
                    new DtoMessages("description", "Description is required.")
            );
        }
        
        if (req.getTransactionDate() == null) {
            listError.add(
                    new DtoMessages("transactionDate", "Transaction Date is required.")
            );
        }

        if (!Arrays.asList(CommonConstant.CREDIT, CommonConstant.DEBIT)
                .contains(req.getPaymentType().toUpperCase())) {
            listError.add(
                    new DtoMessages("paymentType", "Invalid payment type.")
            );
        }
        
        if (!listError.isEmpty()) {
            throw new TransactionValidationException(listError);
        }

    }

}
