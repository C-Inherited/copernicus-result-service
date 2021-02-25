package com.copernicus.resultservice.service.interfaces;

import com.copernicus.resultservice.DTO.RequestDTO;
import com.copernicus.resultservice.enums.Status;

public interface IResultService {

    Status getResult(Integer id);
    Integer changeStatus(RequestDTO requestDTO);

}
