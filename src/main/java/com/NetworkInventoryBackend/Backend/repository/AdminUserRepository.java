package com.NetworkInventoryBackend.Backend.repository;

import com.NetworkInventoryBackend.Backend.model.AdminUser;
import com.NetworkInventoryBackend.Backend.model.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AdminUserRepository extends MongoRepository<AdminUser, String> {
    public AdminUser findByAdminName(String adminUserName);
}
