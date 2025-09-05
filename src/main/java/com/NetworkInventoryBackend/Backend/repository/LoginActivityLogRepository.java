package com.NetworkInventoryBackend.Backend.repository;

import com.NetworkInventoryBackend.Backend.model.LoginActivityLog;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface LoginActivityLogRepository extends MongoRepository<LoginActivityLog, String> {

}
