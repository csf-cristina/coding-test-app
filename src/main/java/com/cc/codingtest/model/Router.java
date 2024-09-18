package com.cc.codingtest.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Size;

@Schema(name = "Router", description = "Representation of a Router and its key attributes")
public class Router {

    private Integer id;

    @Size(min = 1, max = 50)
    private String name;

    @JsonProperty("location_id")
    private Integer locationId;

    @JsonProperty("router_links")
    private Integer[] routerLinks;

    public Router(Integer id, String name, Integer locationId, Integer[] routerLinks) {
        this.id = id;
        this.name = name;
        this.locationId = locationId;
        this.routerLinks = routerLinks;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getLocationId() {
        return locationId;
    }

    public void setLocationId(Integer locationId) {
        this.locationId = locationId;
    }

    public Integer[] getRouterLinks() {
        return routerLinks;
    }

    public void setRouterLinks(Integer[] routerLinks) {
        this.routerLinks = routerLinks;
    }

    @Override
    public String toString() {
        return "Router{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", locationId=" + locationId +
                ", routerLinks=" + routerLinks +
                '}';
    }
}
