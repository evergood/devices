package com.lt.demo.controller;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import com.lt.demo.domen.entity.Project;
import com.lt.demo.service.DeviceService;
import com.lt.demo.service.ProjectService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@Slf4j
public class DemoRestController {

    @Autowired
    private DeviceService deviceService;

    @Autowired
    private ProjectService projectService;

    @GetMapping("/info/{projectId}")
    public List<Project> getProjectInfo(@PathVariable int projectId) {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonElements = new JSONArray();

        return null;
    }

    @GetMapping("/info")
    public JSONObject getAllProjectsInfo() {
        JSONObject jsonObject = new JSONObject();
        JSONArray jsonElements = new JSONArray();

        for (Project project : projectService.getAllProjects()) {
            jsonElements.add(project.getName());
        }
        jsonObject.put("name", jsonElements);

        return jsonObject;
    }
}
