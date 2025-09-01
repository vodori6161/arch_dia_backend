package com.NetworkInventoryBackend.Backend.repository;

import com.NetworkInventoryBackend.Backend.model.DeletedDevice;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface DeletedDeviceRepository extends MongoRepository<DeletedDevice, String> {

    Optional<DeletedDevice> findByIpAddress(String ip_address);
    void deleteByIpAddress(String ipAddress);

}
