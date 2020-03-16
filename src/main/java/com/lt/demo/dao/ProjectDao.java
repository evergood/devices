package com.lt.demo.dao;

import com.lt.demo.domen.entity.Project;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
public class ProjectDao {

    @Autowired
    private EntityManager entityManager;

    public List<Project> findAllProjects() {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Project> query = currentSession.createQuery("FROM Project", Project.class);
        return query.list();
    }

    public Project findProjectById(Integer projectId) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Project> query = currentSession.createQuery("FROM Project p WHERE p.id=:id", Project.class);
        query.setParameter("id", projectId);
        return query.getSingleResult();
    }
}
