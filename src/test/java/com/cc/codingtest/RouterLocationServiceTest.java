package com.cc.codingtest;

import com.cc.codingtest.config.AppConfig;
import com.cc.codingtest.exception.CustomException;
import com.cc.codingtest.model.Location;
import com.cc.codingtest.model.Router;
import com.cc.codingtest.model.RouterLocationResponse;
import com.cc.codingtest.service.RouterLocationService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;
import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

class RouterLocationServiceTest {

    @Mock
    private RestClient.RequestHeadersUriSpec requestHeadersUriSpec;

    @Mock
    private RestClient.RequestHeadersSpec requestHeadersSpec;

    @Mock
    private RestClient.ResponseSpec responseSpec;

    @Mock
    private RestClient restClient;

    @Mock
    private AppConfig appConfig;

    @InjectMocks
    private RouterLocationService routerLocationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        when(restClient.get()).thenReturn(requestHeadersUriSpec);
        when(requestHeadersUriSpec.uri("http://test.com/api")).thenReturn(requestHeadersSpec);
        when(requestHeadersSpec.retrieve()).thenReturn(responseSpec);
        when(appConfig.getRouterLocationUrl()).thenReturn("http://test.com/api");
    }

    private RouterLocationResponse createMockRouterLocationResponse() {
        List<Router> routers = Arrays.asList(
                new Router(1, "router1", 1, new Integer[]{2}),
                new Router(2, "router2", 2, new Integer[]{1,3}),
                new Router(3, "router3", 3, new Integer[]{1})
        );
        List<Location> locations = Arrays.asList(
                new Location(1, "IP5","location1"),
                new Location(2, "IP5","location2"),
                new Location(3, "IP5","location3")
        );
        return new RouterLocationResponse(routers, locations);
    }

    @Test
    void testProcessRouterLocation_Success() throws CustomException {
        RouterLocationResponse mockResponse = createMockRouterLocationResponse();
        when(routerLocationService.fetchRouterLocations()).thenReturn(mockResponse);

        Set<String> result = routerLocationService.processRouterLocation();

        assertEquals(3, result.size());
        assertTrue(result.contains("location2 <-> location3"));
        assertTrue(result.contains("location1 <-> location2"));
        assertTrue(result.contains("location1 <-> location3"));
    }

    @Test
    void testProcessRouterLocation_EmptyResponse() throws CustomException {
        RouterLocationResponse emptyResponse = new RouterLocationResponse(new ArrayList<>(), new ArrayList<>());
        when(routerLocationService.fetchRouterLocations()).thenReturn(emptyResponse);

        Set<String> result = routerLocationService.processRouterLocation();

        assertTrue(result.isEmpty());
    }

    @Test
    void testFetchRouterLocations_missingApiUrl() {
        when(appConfig.getRouterLocationUrl()).thenReturn("");

        CustomException exception = assertThrows(CustomException.class, () -> {
            routerLocationService.fetchRouterLocations();
        });

        assertEquals("API URL is missing", exception.getMessage());
    }

    @Test
    void testFetchRouterLocations_RestClientException() {
        when(requestHeadersSpec.retrieve()).thenThrow(new RestClientException("RestClient error"));

        CustomException exception = assertThrows(CustomException.class, () -> {
            routerLocationService.fetchRouterLocations();
        });

        assertEquals("Error fetching data", exception.getMessage());
    }
}
