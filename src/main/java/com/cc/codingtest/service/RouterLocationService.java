package com.cc.codingtest.service;

import com.cc.codingtest.config.AppConfig;
import com.cc.codingtest.exception.CustomException;
import com.cc.codingtest.model.Location;
import com.cc.codingtest.model.Router;
import com.cc.codingtest.model.RouterLocationResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;
import org.springframework.web.client.RestClientException;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.*;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Service class for fetching and processing Router + Location data
 * Contains logic for fetching the data from the API and processing the response
 */
@Service
public class RouterLocationService {

    private final RestClient restClient;
    private final AppConfig appConfig;
    private final Logger logger = LoggerFactory.getLogger(RouterLocationService.class);

    public RouterLocationService(RestClient restClient, AppConfig appConfig) {
        this.restClient = restClient;
        this.appConfig = appConfig;
    }

    /**
     * Fetches Router + Location data from an API
     * Processes the data to output a list of unique connections by their locations
     *
     * @return Set of Strings
     */
    public Set<String> processRouterLocation() throws CustomException {
        RouterLocationResponse response = fetchRouterLocations();

        Set<String> locationLinks = new HashSet<>();

        Map<Integer, Router> routers = convertRouterListToMap(response.getRouters());
        Map<Integer, Location> locations = convertLocationListToMap(response.getLocations());

        response.getRouters().forEach(router -> {
            Arrays.stream(router.getRouterLinks()).forEach(routerLink -> {
                String routerLocationName = locations.get(router.getLocationId()).getName();
                String linkedLocationName = locations.get(routers.get(routerLink).getLocationId()).getName();

                // Create a sorted pair of location names
                String connection = (routerLocationName.compareTo(linkedLocationName) < 0)
                        ? routerLocationName + " <-> " + linkedLocationName
                        : linkedLocationName + " <-> " + routerLocationName;

                locationLinks.add(connection);

            });
        });

        locationLinks.forEach(link -> System.out.println(link)); //should be using the logger as declared above - but using this for a 'nicer' output
        return locationLinks;
    }

    /**
     * Fetch Router + Location data from API
     * Expects format to be of RouterLocationResponse
     * Loads URL from AppConfig
     *
     * @return RouterLocationResponse object
     * @see RouterLocationResponse
     * @see AppConfig
     * @see WebClient
     */
    public RouterLocationResponse fetchRouterLocations() throws CustomException {
        String url = appConfig.getRouterLocationUrl();

        if (url == null || url.isEmpty()) {
            throw new CustomException("API URL is missing", "The configured API URL is either null or empty");
        }

        try {
            return restClient.get()
                    .uri(url)
                    .retrieve()
                    .body(RouterLocationResponse.class);
        } catch (RestClientException e) {
            throw new CustomException("Error fetching data", e.getMessage());
        }
    }

    /**
     * Outputs a Map of Router objects, using the Id attribute as key.
     * Allows easy lookup of Router objects
     *
     * @param routers of type List
     * @return a Map structure
     */
    private Map<Integer, Router> convertRouterListToMap(List<Router> routers) {
        return routers.stream()
                .collect(Collectors.toMap(Router::getId, router -> router));
    }

    /**
     * Outputs a Map of Location objects, using the Id attribute as key.
     * Allows easy lookup of Router objects
     *
     * @param locations of type List
     * @return a Map structure
     */
    private Map<Integer, Location> convertLocationListToMap(List<Location> locations) {
        return locations.stream()
                .collect(Collectors.toMap(Location::getId, location -> location));
    }
}
