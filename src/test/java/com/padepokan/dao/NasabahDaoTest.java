package com.padepokan.dao;

import com.padepokan.model.Nasabah;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 *
 * @author reza.mr
 */
@SpringBootTest
public class NasabahDaoTest {
    
    @Autowired
    private NasabahDao nasabahDao;
    
    private String name = "Reza";
    private Long accountId = 0L;
    
//    @Test
    public void InsertNasabahDaoTest() {
        
        Nasabah nasabah;
        nasabah = new Nasabah();
        nasabah.setName(name);
        nasabah = nasabahDao.save(nasabah);
        
        Assertions.assertNotNull(nasabah.getAccountId());
        Assertions.assertNotNull(nasabah.getName());
        Assertions.assertNotNull(nasabah.getCreatedBy());
        Assertions.assertNotNull(nasabah.getCreatedDate());
        Assertions.assertNotNull(nasabah.getModifiedBy());
        Assertions.assertNotNull(nasabah.getModifiedDate());
        
    }
    
//    @Test
    public void UpdateNasabahDaoTest() {
        
        name = "Reza Testing";
        Nasabah nasabah;
        nasabah = nasabahDao.findById(accountId).get();
        
        Assertions.assertNotNull(nasabah);
        nasabah.setName(name);
        nasabah = nasabahDao.save(nasabah);
        
        Assertions.assertEquals(nasabah.getName(), name);
        Assertions.assertNotEquals(nasabah.getCreatedDate(), nasabah.getModifiedDate());
        
    }
    
//    @Test 
    public void DeleteNasabahDaoTest() {
        
        nasabahDao.deleteById(accountId);
        Assertions.assertFalse(
                nasabahDao.existsById(accountId)
        );
        
    }
    
}
