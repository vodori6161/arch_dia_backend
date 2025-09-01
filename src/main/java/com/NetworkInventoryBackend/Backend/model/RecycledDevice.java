package com.NetworkInventoryBackend.Backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document("recycled_devices")
@AllArgsConstructor
public class RecycledDevice {
    @Id
    private String deviceId;
    private String deviceName;
    private String description;
    private String timeUp;

    private String ipAddress;
    private String status;
}
