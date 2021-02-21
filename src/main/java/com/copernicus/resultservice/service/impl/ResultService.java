package com.copernicus.resultservice.service.impl;

import com.copernicus.resultservice.DTO.OpportunityDTO;
import com.copernicus.resultservice.DTO.RequestDTO;
import com.copernicus.resultservice.client.OpportunityClient;
import com.copernicus.resultservice.enums.Status;
import com.copernicus.resultservice.service.interfaces.IResultService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Service
public class ResultService implements IResultService {

    @Autowired
    OpportunityClient opportunityClient;

    private final CircuitBreakerFactory circuitBreakerFactory = new Resilience4JCircuitBreakerFactory();

    public Status getResult(Integer id) {
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("result-service");
        return circuitBreaker.run(() -> Status.valueOf(opportunityClient.getOpportunity(id).getStatus()), throwable -> statusCache());
    }

    private Status statusCache() {
        throw new ResponseStatusException(HttpStatus.NOT_ACCEPTABLE, "The status of this account is uncertain");
    }

    public Integer changeStatus(RequestDTO requestDTO) {
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("result-service");
        OpportunityDTO opportunityDTO = circuitBreaker.run(() -> opportunityClient.getOpportunity(requestDTO.getId()), throwable -> opportunityCache());
        opportunityDTO.setStatus(requestDTO.getStatus());
        circuitBreaker.run(() -> opportunityClient.postOpportunity(opportunityDTO), throwable -> opportunityCache());
        if (Objects.equals(Status.CLOSED_LOST, opportunityDTO.getStatus())){
            return -1;
        }
        if (Objects.equals(Status.CLOSED_WON, opportunityDTO.getStatus())){
            return 1;
        }
        return 0;
    }

    private OpportunityDTO opportunityCache() {
        throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This is doesn't appear on our database. Please try again.");

    }
}
