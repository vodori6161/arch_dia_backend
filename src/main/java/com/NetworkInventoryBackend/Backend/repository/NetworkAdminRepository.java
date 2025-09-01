package com.NetworkInventoryBackend.Backend.repository;

import com.NetworkInventoryBackend.Backend.model.AdminUser;
import com.NetworkInventoryBackend.Backend.model.NetworkAdmin;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NetworkAdminRepository extends MongoRepository<NetworkAdmin,String> {
    public NetworkAdmin findByNetworkUsername(String networkUsername);
}
