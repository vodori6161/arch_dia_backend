package com.NetworkInventoryBackend.Backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class AdminUserDto {
    @Id
    private String adminId;
    private String adminName;
    private String adminPassword;

}