package com.NetworkInventoryBackend.Backend.service;

import com.NetworkInventoryBackend.Backend.dto.AdminUserDto;
import com.NetworkInventoryBackend.Backend.dto.NetworkAdminDto;
import com.NetworkInventoryBackend.Backend.model.NetworkAdmin;
import jakarta.servlet.http.HttpSession;

public interface NetworkAdminService {
    public NetworkAdminDto createNetworkAdmin(NetworkAdminDto networkAdminDto);
    public Boolean networkAdminLogin(NetworkAdminDto networkAdminDto, HttpSession session);
    public Boolean logout(HttpSession session);
    public AdminUserDto addAdmin(AdminUserDto adminuserDto);
}
