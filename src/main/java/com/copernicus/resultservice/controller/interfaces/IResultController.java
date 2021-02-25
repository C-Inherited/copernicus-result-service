package com.copernicus.resultservice.controller.interfaces;

import com.copernicus.resultservice.DTO.RequestDTO;
import com.copernicus.resultservice.enums.Status;
import org.springframework.web.bind.annotation.RequestHeader;

public interface IResultController {

    Status getResult(Integer id, @RequestHeader(value = "Authorization") String authorizationHeader);
    Integer changeStatus(RequestDTO requestDTO, @RequestHeader(value = "Authorization") String authorizationHeader);
}
