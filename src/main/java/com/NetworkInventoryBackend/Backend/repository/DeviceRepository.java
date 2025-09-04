package com.NetworkInventoryBackend.Backend.repository;

import com.NetworkInventoryBackend.Backend.dto.DeviceDto;
import com.NetworkInventoryBackend.Backend.model.Device;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository

public interface DeviceRepository extends MongoRepository<Device,String> {


    Optional<Device> findByIpAddress(String ipAddress);
    Boolean findByDeviceId(String deviceId);



}
