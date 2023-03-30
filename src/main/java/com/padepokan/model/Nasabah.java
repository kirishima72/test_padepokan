package com.padepokan.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

/**
 *
 * @author reza.mr
 */
@Entity
@EntityListeners(AuditingEntityListener.class)
@Table(name = "Nasabah")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Nasabah extends DefaultModel implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "account_id", updatable = false, insertable = false)
    private long accountId;

    @Column(name = "name")
    private String name;

}
