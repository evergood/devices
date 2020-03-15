package com.lt.demo.controller;

import com.lt.demo.service.DeviceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;

@RestController
@Slf4j
public class DemoRestController {

    @Autowired
    private DeviceService deviceService;

    @GetMapping("/info/{projectId}")
    public BigInteger getProjectInfo(@PathVariable int projectId) {
        return deviceService.getStableCount(projectId);
    }

    @GetMapping("/info")
    public String getAllProjectsInfo() {
        return null;
    }
}
