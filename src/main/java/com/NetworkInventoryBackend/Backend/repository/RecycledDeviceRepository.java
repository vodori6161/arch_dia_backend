package com.NetworkInventoryBackend.Backend.repository;

import com.NetworkInventoryBackend.Backend.model.RecycledDevice;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface RecycledDeviceRepository extends MongoRepository<RecycledDevice, String> {
}
