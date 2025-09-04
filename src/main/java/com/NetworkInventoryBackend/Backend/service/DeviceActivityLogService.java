package com.NetworkInventoryBackend.Backend.service;

import com.NetworkInventoryBackend.Backend.model.DeviceActivityLog;
import com.NetworkInventoryBackend.Backend.repository.DeviceActivityLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class DeviceActivityLogService {

    @Autowired
    private DeviceActivityLogRepository deviceActivityLogRepository;

    public void logAction(String userId, String userName, String role,
                          String actionType, String description) {

        DeviceActivityLog log = new DeviceActivityLog();
        log.setUserId(userId);
        log.setUserName(userName);
        log.setRole(role);
        log.setActionType(actionType);
        log.setDescription(description);
        log.setTimestamp(LocalDateTime.now());

        deviceActivityLogRepository.save(log);
    }
}

