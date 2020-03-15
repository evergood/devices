package com.lt.demo.dao;

import org.hibernate.Session;
import org.hibernate.query.NativeQuery;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.math.BigInteger;

@Repository
public class DeviceDao {

    @Autowired
    private EntityManager entityManager;

    public Long countDevices(Integer projectId) {
        Session currentSession = entityManager.unwrap(Session.class);
        Query<Long> query = currentSession.createQuery("SELECT COUNT(*) FROM Device AS d WHERE d.project.id =: projectId",
                Long.class);
        query.setParameter("projectId", projectId);
        return query.getSingleResult();
    }

    public BigInteger getDevicesWithErrors(Integer projectId) {
        Session currentSession = entityManager.unwrap(Session.class);
        NativeQuery<BigInteger> query = currentSession.createNativeQuery("SELECT COUNT(DISTINCT devices.device_id)\n" +
                "FROM (devices JOIN events ON devices.device_id = events.device_id)\n" +
                "WHERE project_id = ?\n" +
                "AND (events.type = 'error' OR events.type = 'warning')\n" +
                "AND (DATE_PART('day', CURRENT_TIMESTAMP - events.date) * 24 +\n" +
                "    DATE_PART('hour', CURRENT_TIMESTAMP - events.date) < 24)");
        query.setParameter(1, projectId);
        return query.getResultList().get(0);
    }

    public BigInteger getStableDevices(Integer projectId) {
        Session currentSession = entityManager.unwrap(Session.class);
        NativeQuery<BigInteger> query = currentSession.createNativeQuery("SELECT count(DISTINCT devices.device_id) -\n" +
                "  count(DISTINCT devices.device_id) FILTER (WHERE type = 'error' OR type = 'warning' )\n" +
                "FROM (devices JOIN events ON devices.device_id = events.device_id)\n" +
                "WHERE project_id = ?");
        query.setParameter(1, projectId);
        return query.getResultList().get(0);
    }
}
