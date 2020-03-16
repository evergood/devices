package com.lt.demo.service;

import com.lt.demo.dao.DeviceDao;
import com.lt.demo.domen.Type;
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

    public BigInteger countErrors(Integer deviceId) {
        return deviceDao.countFromDevice(deviceId, Type.ERROR);
    }

    public BigInteger countWarnings(Integer deviceId) {
        return deviceDao.countFromDevice(deviceId, Type.WARNING);
    }

    public BigInteger countEvents(Integer deviceId) {
        return deviceDao.countFromDevice(deviceId, Type.EVENT);
    }

    public boolean hasErrors(Integer deviceId) {
        return countWarnings(deviceId).compareTo(BigInteger.ZERO) > 0 ||
                countErrors(deviceId).compareTo(BigInteger.ZERO) > 0;
    }
}
