package com.cc.codingtest.controller;

import com.cc.codingtest.service.RouterLocationService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
@RequestMapping(path = "/api/v1/service/routerLocations")
@Tag(name = "Router Location", description = "API for fetching a set of routers and their links, returning a unique set of links between locations")
public class RouterLocationController {

    @Autowired
    RouterLocationService routerLocationService;

    @GetMapping
    public ResponseEntity<Set<String>> getRouterLocations() {
        Set<String> routerLinks = routerLocationService.processRouterLocation();
        return ResponseEntity.ok(routerLinks);
    }
}

