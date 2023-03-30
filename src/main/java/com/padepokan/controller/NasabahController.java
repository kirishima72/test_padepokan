package com.padepokan.controller;

import com.padepokan.exception.NasabahNotFoundException;
import com.padepokan.dao.NasabahDao;
import com.padepokan.dto.DtoResponse;
import com.padepokan.model.Nasabah;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author reza.mr
 */
@RestController
@RequestMapping("/api/nasabah/")
public class NasabahController {

    @Autowired
    private NasabahDao nasabahDao;

    @PostMapping(
            value = "create",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public DtoResponse createNasabah(@RequestBody Map<String, Object> req) throws NasabahNotFoundException {

        if (!req.containsKey("name")) {
            throw new NasabahNotFoundException("Field name is required.");
        }
        
        if (StringUtils.isEmpty(req.get("name"))) {
            throw new NasabahNotFoundException("Name can not be empty.");
        }

        Nasabah nasabah = new Nasabah();
        nasabah.setName(req.get("name").toString());
        nasabah = nasabahDao.save(nasabah);

        Map<String, Object> data = new HashMap<>();
        data.put("accountId", nasabah.getAccountId());
        data.put("name", nasabah.getName());

        DtoResponse response = new DtoResponse();
        response.setStatus("1");
        response.setMessage("Success");
        response.setData(Arrays.asList(data));
        return response;
    }
    
    @GetMapping(
            value = "remove/{id}",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public DtoResponse removeNasabah(@PathVariable(name = "id") long accountId) throws NasabahNotFoundException {
        nasabahDao.deleteById(accountId);
        DtoResponse response = new DtoResponse();
        response.setStatus("1");
        response.setMessage(String.format("Success deleted User with id [%d]", accountId));
        return response;
    }
    
    @PostMapping(
            value = "update",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public DtoResponse updateNasabah(@RequestBody Map<String, Object> req) throws NasabahNotFoundException {
        validateRequestParamsNasabah(req);
        
        Long accountId = Long.valueOf(req.get("accountId").toString());
        Nasabah nasabah = nasabahDao.findById(accountId)
                .orElseThrow(() -> new NasabahNotFoundException("Nasabah not found."));
        nasabah.setName(req.get("name").toString());
        nasabah = nasabahDao.save(nasabah);

        Map<String, Object> data = new HashMap<>();
        data.put("accountId", nasabah.getAccountId());
        data.put("name", nasabah.getName());

        DtoResponse response = new DtoResponse();
        response.setStatus("1");
        response.setMessage(String.format("Nasabah with id[%d] succussfully updated.", accountId));
        response.setData(Arrays.asList(data));
        return response;
    }
   
    private static void validateRequestParamsNasabah(Map<String, Object> req) throws NasabahNotFoundException {
        if (!req.containsKey("name")) {
            throw new NasabahNotFoundException("Field name is required.");
        }
        
        if (!req.containsKey("accountId")) {
            throw new NasabahNotFoundException("Field accountId is required.");
        }
        
        if (StringUtils.isEmpty(req.get("name"))) {
            throw new NasabahNotFoundException("Name can not be empty.");
        }
        
        if (StringUtils.isEmpty(req.get("name"))) {
            throw new NasabahNotFoundException("AccountId can not be empty.");
        }
    }
    
}
