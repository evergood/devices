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

@RestController
@Slf4j
public class DemoRestController {

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private ProjectService projectService;

    @GetMapping("/info/{projectId}")
    public JSONObject getProjectInfo(@PathVariable int projectId) {

        Project project = projectService.getProjectById(projectId);
        JSONObject deviceObject = new JSONObject();
        for (Device device : project.getDevices()) {
            JSONObject body = new JSONObject();
            body.put("id", device.getId());
            body.put("serialnumber", device.getSerialNumber());
            body.put("projectId", projectId);
            body.put("hasErrors", deviceService.hasErrors(device.getId()));
            JSONObject summary = new JSONObject();
            summary.put("eventCount", deviceService.countEvents(device.getId()));
            summary.put("warningCount", deviceService.countWarnings(device.getId()));
            summary.put("errorCount", deviceService.countErrors(device.getId()));
            body.put("summaryInfo", summary);
            deviceObject.put(device.getSerialNumber(), body);
        }

        return deviceObject;
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
