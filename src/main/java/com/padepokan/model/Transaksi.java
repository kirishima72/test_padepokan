package com.padepokan.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 *
 * @author reza.mr
 */
@Entity 
@EntityListeners(AuditingEntityListener.class)
@Table(name = "Transaksi")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
@ToString
public class Transaksi extends DefaultModel {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "transaction_id", updatable = false, insertable = false)
    private long transactionId;
    
    @Column(name = "transaction_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date transactionDate;
    
    @Column(name = "description", nullable = false)
    private String description;
    
    @Column(name = "debit_credit_status", nullable = false)
    private String status;
    
    @Column(name = "amount", nullable = false)
    private BigDecimal amount;
    
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "transaction_account_id")
    private Nasabah nasabah;
    
}
