package com.NetworkInventoryBackend.Backend.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface RecycledDeviceRepository extends MongoRepository<RecycledDeviceRepository, String> {
}
