package com.padepokan.dao;

import com.padepokan.model.Transaksi;
import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

/**
 *
 * @author reza.mr
 */
@Repository
public interface TransaksiDao extends JpaRepository<Transaksi, Long> {

    @Query(value = "SELECT * FROM Transaksi "
            + "WHERE transaction_date BETWEEN :startDate AND :endDate "
            + "AND transaction_account_id = :transactionAccountId ",
            nativeQuery = true)
    List<Transaksi> getAllBetweenDates(
            @Param("startDate") Date startDate,
            @Param("endDate") Date endDate, 
            @Param("transactionAccountId") long transactionAccountId);

}
