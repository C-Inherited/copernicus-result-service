package com.copernicus.resultservice.security;

import com.copernicus.resultservice.DTO.AuthenticationRequest;
import com.copernicus.resultservice.client.OpportunityClient;
import com.copernicus.resultservice.controller.impl.ResultController;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.circuitbreaker.resilience4j.Resilience4JCircuitBreakerFactory;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Objects;

@Component
public class RegistrationRoutine {

    @Autowired
    OpportunityClient opportunityClient;

    @Autowired
    ResultController resultController;

    public static boolean isOpportunityRegistered = false;

    private static final Logger log = LoggerFactory.getLogger(RegistrationRoutine.class);

    private static final SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss");

    private final CircuitBreakerFactory circuitBreakerFactory = new Resilience4JCircuitBreakerFactory();

    @Scheduled(fixedRate = 10000)
    public void checkOpportunityRegistration() {
            if (!isOpportunityRegistered){
                CircuitBreaker circuitBreaker = circuitBreakerFactory.create("opportunity-service");
                log.info("Trying to register with validation-service {}", dateFormat.format(new Date()));
                AuthenticationRequest authenticationRequest = new AuthenticationRequest("result-service", "result-service");
                ResponseEntity<?> responseEntity= circuitBreaker.run(() -> opportunityClient.createAuthenticationToken(authenticationRequest), throwable -> fallbackTransaction("opportunity-service"));
                if (responseEntity != null) {
                    parseJWTAccount(responseEntity);
                    isOpportunityRegistered = true;
                    log.info("Registered with contact-service auth token: {}", ResultController.getStatusAuthOk());
                }
            }
    }

    private void parseJWTAccount(ResponseEntity<?> responseEntity) {
        String auth = Objects.requireNonNull(responseEntity.getBody()).toString();
        ResultController.setStatusAuthOk(auth.substring(5, auth.length() - 1));
    }

    private ResponseEntity<?> fallbackTransaction(String serviceName) {
        log.info( serviceName + " is not reachable {}", dateFormat.format(new Date()));
        return null;
    }
}
