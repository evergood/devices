package com.lt.demo.controller;

import com.lt.demo.domen.entity.Device;
import com.lt.demo.domen.entity.Project;
import com.lt.demo.service.DeviceService;
import com.lt.demo.service.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigInteger;
import java.util.List;

@RestController
@Slf4j
public class DemoRestController {

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private ProjectService projectService;

    @GetMapping("/info/{projectId}")
    public BigInteger getProjectInfo(@PathVariable int projectId) {


        return deviceService.countErrors(projectId);
    }

    @GetMapping("/info")
    public JSONArray getAllProjectsInfo() {
        JSONArray output = new JSONArray();
        for (Project project : projectService.getAllProjects()) {

            JSONObject projectObject = new JSONObject();
            projectObject.put("id", project.getId());
            projectObject.put("projectName", project.getName());
            JSONObject stats = new JSONObject();
            stats.put("deviceCount", deviceService.getDeviceCount(project.getId()));
            stats.put("deviceWithErrors", deviceService.getErrorCount(project.getId()));
            stats.put("stable devices", deviceService.getStableCount(project.getId()));
            JSONArray devices = new JSONArray();
            for (Device device : project.getDevices()) {
                devices.add(device.getSerialNumber());
            }
            projectObject.put("stats", stats);
            projectObject.put("devices", devices);
            output.add(projectObject);
        }
        return output;
    }
}
