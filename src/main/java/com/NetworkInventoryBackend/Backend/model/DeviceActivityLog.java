package com.NetworkInventoryBackend.Backend.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Document(collection = "device_activity_logs")
public class DeviceActivityLog {
    @Id
    private String id;

    private String userId;
    private String userName;
    private String role; // "NetworkAdmin", "Admin", "User"

    private String actionType; // ADD_DEVICE / UPDATE_DEVICE / DELETE_DEVICE / RESTORE_DEVICE
    private String description;

    private LocalDateTime timestamp;

}
