package com.NetworkInventoryBackend.Backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "devicelist")
public class Device {
    @Id
    private String deviceId;

    @Indexed
    private String deviceName;

    private String description;
    private String timeUp;

    @Indexed(unique = true)
    private String ipAddress;

    private String status;

    @Indexed
    private String location;

    private String createdAt;
}
