package com.NetworkInventoryBackend.Backend.model;

import org.springframework.data.annotation.Id;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Data // Generates getters, setters, toString, equals, hashCode
@NoArgsConstructor
@AllArgsConstructor
@Builder // Lets you use ActivityLog.builder()...
@Document(collection = "login_activity_logs")

public class LoginActivityLog {
    @Id
    private String id;

    private String userId;
    private String userName;
    private String role;        // "NetworkAdmin", "Admin", "User"
    private String actionType; // LOGIN / LOGOUT
    private String description;
    private LocalDateTime timestamp;

}
