package com.padepokan.controller;

import com.padepokan.dao.PointDao;
import com.padepokan.dto.DtoPoint;
import com.padepokan.dto.DtoResponse;
import com.padepokan.exception.NasabahNotFoundException;
import com.padepokan.model.Point;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author reza.mr
 */
@RestController
@RequestMapping("/api/point/")
public class PointController {

    @Autowired
    private PointDao pointDao;

    @GetMapping(
            value = "display",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public DtoResponse displayPoint() throws NasabahNotFoundException {
        List<Point> listPoint = pointDao.findAll();

        List<DtoPoint> data = listPoint.stream().map(point
                -> new DtoPoint(
                        point.getNasabah().getAccountId(),
                        point.getNasabah().getName(),
                        point.getPoint())
        ).collect(Collectors.toList());

        DtoResponse response = new DtoResponse();
        response.setStatus("1");
        response.setMessage("Success");
        response.setData(data);
        return response;
    }

}
