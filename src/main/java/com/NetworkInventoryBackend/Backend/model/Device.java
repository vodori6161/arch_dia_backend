package com.NetworkInventoryBackend.Backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "devicelist")
public class Device {
    @Id
    private String deviceId;
    private String deviceName;
    private String description;
    private String timeUp;
    private String ipAddress;
    private String status;
    private String location; //added


}
