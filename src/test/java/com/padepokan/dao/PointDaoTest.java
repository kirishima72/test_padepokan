package com.padepokan.dao;

import com.padepokan.constant.CommonConstant;
import com.padepokan.model.Nasabah;
import com.padepokan.model.Point;
import com.padepokan.model.Transaksi;
import com.padepokan.helper.Helper;
import jakarta.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.jdbc.Sql;

/**
 *
 * @author reza.mr
 */
@SpringBootTest
@Sql(scripts = {"classpath:delete-nasabah.sql", "classpath:insert-nasabah.sql"})
public class PointDaoTest {

    @Autowired
    private TransaksiDao transaksiDao;

    @Autowired
    private PointDao pointDao;

    @Autowired
    private NasabahDao nasabahDao;

//    @Test
//    @Transactional
    public void InsertPointTest() throws InterruptedException {

        Nasabah nasabah = nasabahDao.findById(1L).get();

        Transaksi transaksi = new Transaksi();
        transaksi.setNasabah(nasabah);
        transaksi.setAmount(new BigDecimal(111_000));
        transaksi.setDescription(CommonConstant.BELI_PULSA);
        transaksi.setStatus(CommonConstant.DEBIT);
        transaksi.setTransactionDate(new Date());
        transaksi = transaksiDao.save(transaksi);

        System.out.println(transaksi.toString());
        
        Assertions.assertNotNull(transaksi);

        long gainPoint = 0L;
        if (Helper.isGainPoint(transaksi.getDescription())) {
            gainPoint = Helper.calcPoint(transaksi.getAmount().doubleValue());
        }
        
        Point point = new Point();
        point.setNasabah(nasabah);
        point.setPoint(gainPoint);
        
        System.out.println("Point: " + point.getPoint());
        System.out.println("PointId: " + point.getPointId());

        Optional<Point> dataPoint = pointDao.findById(nasabah.getAccountId());
        if (dataPoint.isPresent()) {
            point.setPoint(gainPoint + dataPoint.get().getPoint());
        }

        point = pointDao.save(point);
        pointDao.flush();
        Assertions.assertNotNull(point);
    }

}
