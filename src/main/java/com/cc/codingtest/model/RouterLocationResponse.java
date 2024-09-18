package com.cc.codingtest.model;

import io.swagger.v3.oas.annotations.media.Schema;

import java.util.List;

@Schema(name = "RouterLocationResponse", description = "Model class for the response expected by the API for fetching routers and their locations")
public class RouterLocationResponse {

    private List<Router> routers;
    private List<Location> locations;

    public RouterLocationResponse(List<Router> routers, List<Location> locations) {
        this.routers = routers;
        this.locations = locations;
    }

    public List<Router> getRouters() {
        return routers;
    }

    public void setRouters(List<Router> routers) {
        this.routers = routers;
    }

    public List<Location> getLocations() {
        return locations;
    }

    public void setLocations(List<Location> locations) {
        this.locations = locations;
    }

    @Override
    public String toString() {
        return "RouterLocationResponse{" +
                "routers=" + routers +
                ", locations=" + locations +
                '}';
    }
}
