package com.NetworkInventoryBackend.Backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeviceDto {
    @Id
    private String deviceId;
    private String deviceName;
    private String description;
    private String timeUp;
    private String ipAddress;
    private String status;
    private String location; // added
    private String createdAt;
}
