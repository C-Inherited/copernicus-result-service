package com.copernicus.resultservice.controller.interfaces;

import com.copernicus.resultservice.DTO.RequestDTO;
import com.copernicus.resultservice.enums.Status;
import org.springframework.web.bind.annotation.RequestHeader;

public interface IResultController {

    Status getResult(Integer id);
    Integer changeStatus(RequestDTO requestDTO);
}
