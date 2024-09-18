package com.cc.codingtest;

import com.cc.codingtest.config.AppConfig;
import com.cc.codingtest.controller.RouterLocationController;
import com.cc.codingtest.service.RouterLocationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

public class RouterLocationControllerTest {



    @Mock
    private RouterLocationService routerLocationService;

    @InjectMocks
    private RouterLocationController routerLocationController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetRouterLocations() {
        Set<String> mockRouterLinks = new HashSet<>();
        mockRouterLinks.add("routerLocation1 - route4Location2");
        mockRouterLinks.add("routerLocation3 - routerLocation4");

        when(routerLocationService.processRouterLocation()).thenReturn(mockRouterLinks);

        ResponseEntity<Set<String>> response = routerLocationController.getRouterLocations();

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(mockRouterLinks, response.getBody());
    }
}
