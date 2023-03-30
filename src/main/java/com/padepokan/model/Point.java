package com.padepokan.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.MapsId;
import jakarta.persistence.OneToOne;
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
@Table(name = "Point")
@Data
@NoArgsConstructor
@EqualsAndHashCode(callSuper = false)
public class Point extends DefaultModel implements Serializable {

    @Id
    private long pointId;
    
    @OneToOne(fetch = FetchType.LAZY)
    @MapsId
    @JoinColumn(name = "point_account_id")
    private Nasabah nasabah;
    
    @Column(name = "point_nasabah", nullable = false)
    private long point;

}
