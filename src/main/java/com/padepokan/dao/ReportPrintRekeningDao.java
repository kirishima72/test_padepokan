package com.padepokan.dao;

import com.padepokan.model.ReportPrintRekening;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author reza.mr
 */

@Repository
public interface ReportPrintRekeningDao 
        extends JpaRepository<ReportPrintRekening, Long> {
    
}
