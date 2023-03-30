package com.padepokan.dao;

import com.padepokan.model.Point;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author reza.mr
 */
@Repository
public interface PointDao extends JpaRepository<Point, Long>{
    
}
