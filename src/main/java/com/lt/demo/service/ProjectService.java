package com.lt.demo.service;

import com.lt.demo.dao.ProjectDao;
import com.lt.demo.domen.entity.Project;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProjectService {

    @Autowired
    private ProjectDao projectDao;

    public List<Project> getAllProjects() {
        return projectDao.findAllProjects();
    }

    public Project getProjectById(Integer projectId) {
        return projectDao.findProjectById(projectId);
    }
}
