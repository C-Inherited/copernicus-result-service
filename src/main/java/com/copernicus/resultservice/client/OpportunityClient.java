package com.copernicus.resultservice.client;

import com.copernicus.resultservice.DTO.AuthenticationRequest;
import com.copernicus.resultservice.DTO.OpportunityDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name = "opportunity-service")
public interface OpportunityClient {

    @GetMapping("/opportunity/{id}")
    OpportunityDTO getOpportunity(@PathVariable Integer id, @RequestHeader(value = "Authorization") String authorizationHeader);

    @PostMapping("/opportunity")
    OpportunityDTO postOpportunity(@RequestBody OpportunityDTO opportunityDTO, @RequestHeader(value = "Authorization") String authorizationHeader);

    @PostMapping("opportunity/authenticate")
    ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest);
}
