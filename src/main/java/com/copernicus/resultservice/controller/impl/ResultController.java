package com.copernicus.resultservice.controller.impl;

import com.copernicus.resultservice.DTO.AuthenticationRequest;
import com.copernicus.resultservice.DTO.AuthenticationResponse;
import com.copernicus.resultservice.DTO.RequestDTO;
import com.copernicus.resultservice.controller.interfaces.IResultController;
import com.copernicus.resultservice.enums.Status;
import com.copernicus.resultservice.security.MyUserDetailsService;
import com.copernicus.resultservice.service.interfaces.IResultService;
import com.copernicus.resultservice.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class ResultController implements IResultController {

    @Autowired
    IResultService resultService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtil jwtTokenUtil;

    @Autowired
    private MyUserDetailsService userDetailsService;

    private static String statusAuthOk;

    @GetMapping("/opportunity-status/{id}")
    public Status getResult(@PathVariable Integer id) {
        return resultService.getResult(id);
    }

    @PostMapping("/opportunity-status/{id}")
    public Integer changeStatus(@RequestBody RequestDTO requestDTO) {
        return resultService.changeStatus(requestDTO);
    }

    /** AUTHENTICATION **/
    @PostMapping("/status/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {

        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authenticationRequest.getUsername(), authenticationRequest.getPassword())
            );
        }
        catch (BadCredentialsException e) {
            throw new Exception("Incorrect username or password", e);
        }


        final UserDetails userDetails = userDetailsService
                .loadUserByUsername(authenticationRequest.getUsername());

        final String jwt = jwtTokenUtil.generateToken(userDetails);

        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    public static String getStatusAuthOk() {
        return statusAuthOk;
    }

    public static void setStatusAuthOk(String statusAuthOk) {
        ResultController.statusAuthOk = statusAuthOk;
    }

}
