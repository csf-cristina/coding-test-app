package com.cc.codingtest.model;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(name = "Location", description = "Representation of a Location and its key attributes")
public class Location {

    private Integer id;
    private String postcode;
    private String name;

    public Location(Integer id, String postcode, String name) {
        this.id = id;
        this.postcode = postcode;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Location{" +
                "id=" + id +
                ", postcode='" + postcode + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
