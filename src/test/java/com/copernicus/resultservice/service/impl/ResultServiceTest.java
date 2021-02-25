package com.copernicus.resultservice.service.impl;

import com.copernicus.resultservice.DTO.OpportunityDTO;
import com.copernicus.resultservice.client.OpportunityClient;
import com.copernicus.resultservice.enums.Product;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ResultServiceTest {

    @Autowired
    OpportunityClient opportunityClient;

    @BeforeEach
    void setUp() {


    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void getResult() {

    }

    @Test
    void changeStatus() {
    }
}