package com.NetworkInventoryBackend.Backend.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "admin_detail")
public class AdminUser {
    @Id
    private String adminId;
    private String adminName;
    private String adminPassword;

}
