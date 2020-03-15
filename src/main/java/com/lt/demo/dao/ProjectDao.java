package com.lt.demo.dao;

import com.lt.demo.entity.Project;
import lombok.RequiredArgsConstructor;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ProjectDao {

    private EntityManager entityManager;

    public List<Project> findAllProjects() {

        Session currentSession = entityManager.unwrap(Session.class);
        Query<Project> query = currentSession.createQuery("FROM Project", Project.class);
        return query.getResultList();
    }
}
