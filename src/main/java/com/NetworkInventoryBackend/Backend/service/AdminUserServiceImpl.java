package com.NetworkInventoryBackend.Backend.service;

import com.NetworkInventoryBackend.Backend.dto.AdminUserDto;
import com.NetworkInventoryBackend.Backend.dto.UserDto;
import com.NetworkInventoryBackend.Backend.model.AdminUser;
import com.NetworkInventoryBackend.Backend.model.User;
import com.NetworkInventoryBackend.Backend.repository.AdminUserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AdminUserServiceImpl implements AdminUserService {

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private AdminUserRepository adminUserRepository;

    @Override
    public AdminUserDto createAdmin(AdminUserDto adminUserDto) {
        AdminUser user = new AdminUser();
        user.setAdminId(UUID.randomUUID().toString());
        user.setAdminName(adminUserDto.getAdminName());

        // Encrypt the password
        String encryptedPassword = passwordEncoder.encode(adminUserDto.getAdminPassword());
        user.setAdminPassword(encryptedPassword);

        // Save to repository
        adminUserRepository.save(user);

        // Return DTO
        return new AdminUserDto(user.getAdminId(), user.getAdminName(), encryptedPassword);
    }

    @Override
    public Boolean adminUserLogin(AdminUserDto adminUserDto, HttpSession session) {
        AdminUser adminUser = adminUserRepository.findByAdminName(adminUserDto.getAdminName());

        if (adminUser != null && passwordEncoder.matches(adminUserDto.getAdminPassword(), adminUser.getAdminPassword())) {
            session.setAttribute("adminId", adminUser.getAdminId());
            session.setAttribute("role", "ADMIN");
            return true;
        }

        return false;
    }

    public Boolean logout(HttpSession session) {
        String role = (String) session.getAttribute("role");
        session.invalidate();
        return true;
    }

}
