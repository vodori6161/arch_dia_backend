package com.NetworkInventoryBackend.Backend.service;

import com.NetworkInventoryBackend.Backend.dto.AdminUserDto;
import com.NetworkInventoryBackend.Backend.dto.UserDto;
import com.NetworkInventoryBackend.Backend.model.AdminUser;
import jakarta.servlet.http.HttpSession;

public interface AdminUserService {
    public AdminUserDto createAdmin(AdminUserDto adminUserDto);
    public Boolean adminUserLogin(AdminUserDto adminUserDto, HttpSession session);
    public Boolean logout(HttpSession session);

}
