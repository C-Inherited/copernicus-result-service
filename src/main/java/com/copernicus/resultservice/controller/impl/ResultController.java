package com.copernicus.resultservice.controller.impl;

import com.copernicus.resultservice.DTO.RequestDTO;
import com.copernicus.resultservice.controller.interfaces.IResultController;
import com.copernicus.resultservice.enums.Status;
import com.copernicus.resultservice.service.interfaces.IResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ResultController implements IResultController {

    @Autowired
    IResultService resultService;

    @GetMapping("/opportunity-status/{id}")
    public Status getResult(@PathVariable Integer id) {
        return resultService.getResult(id);
    }

    @PostMapping("/opportunity-status/{id}")
    public Integer changeStatus(RequestDTO requestDTO) {
        return resultService.changeStatus(requestDTO);
    }
}
