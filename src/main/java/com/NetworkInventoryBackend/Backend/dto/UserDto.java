package com.NetworkInventoryBackend.Backend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    @Id
    private String userId;
    private String userName;
    private String userPassword;
}
