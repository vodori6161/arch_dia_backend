package com.NetworkInventoryBackend.Backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class NetworkAdminDto {

    @Id
    private String networkAdminId;
    private String networkUsername;
    private String password;
}
