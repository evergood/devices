package com.lt.demo.service;

import com.lt.demo.dao.DeviceDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;

@Service
public class DeviceService {

    @Autowired
    private DeviceDao deviceDao;

    public Long getDeviceCount(Integer projectId) {
        return deviceDao.countDevices(projectId);
    }

    public BigInteger getErrorCount(Integer projectId) {
        return deviceDao.getDevicesWithErrors(projectId);
    }

    public BigInteger getStableCount(Integer projectId) {
        return deviceDao.getStableDevices(projectId);
    }
}
