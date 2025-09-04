package com.NetworkInventoryBackend.Backend.service;

import com.NetworkInventoryBackend.Backend.dto.AdminUserDto;
import com.NetworkInventoryBackend.Backend.dto.NetworkAdminDto;
import com.NetworkInventoryBackend.Backend.model.LoginActivityLog;
import com.NetworkInventoryBackend.Backend.model.AdminUser;
import com.NetworkInventoryBackend.Backend.model.NetworkAdmin;
import com.NetworkInventoryBackend.Backend.repository.LoginActivityLogRepository;
import com.NetworkInventoryBackend.Backend.repository.AdminUserRepository;
import com.NetworkInventoryBackend.Backend.repository.NetworkAdminRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;
@Service
public class NetworkAdminServiceImpl implements NetworkAdminService{
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private NetworkAdminRepository networkAdminRepository;

    @Autowired
    private AdminUserRepository adminUserRepository;

    @Autowired
    private LoginActivityLogRepository loginActivityLogRepository;

    @Override
    public NetworkAdminDto createNetworkAdmin(NetworkAdminDto networkAdminDto) {
        NetworkAdmin user = new NetworkAdmin();
        user.setNetworkAdminId(UUID.randomUUID().toString());
        user.setNetworkUsername(networkAdminDto.getNetworkUsername());

        // Encrypt the password
        String encryptedPassword = passwordEncoder.encode(networkAdminDto.getPassword());
        user.setPassword(encryptedPassword);

        networkAdminRepository.save(user);


        // Save user to database (you'll need to inject and use UserRepository here)

        return new NetworkAdminDto(user.getNetworkAdminId(), user.getNetworkUsername(), encryptedPassword);
    }

    @Override
    public Boolean networkAdminLogin(NetworkAdminDto networkAdminDto, HttpSession session) {
        NetworkAdmin user = networkAdminRepository.findByNetworkUsername(networkAdminDto.getNetworkUsername());
        if (user != null && passwordEncoder.matches(networkAdminDto.getPassword(), user.getPassword())) {
            session.setAttribute("NetworkAdminId", user.getNetworkAdminId());
            session.setAttribute("role", "NetworkAdmin");

            // Save login log
            loginActivityLogRepository.save(LoginActivityLog.builder()
                    .userId(user.getNetworkAdminId())
                    .userName(user.getNetworkUsername())
                    .role("NetworkAdmin")
                    .actionType("LOGIN")
                    .description("Network admin logged in")
                    .timestamp(LocalDateTime.now())
                    .build());

            return true;
        }
        return false;
    }

    @Override
    public Boolean logout(HttpSession session) {
        String userId = (String) session.getAttribute("NetworkAdminId");

        if (userId != null) {
            NetworkAdmin user = networkAdminRepository.findById(userId).orElse(null);
            loginActivityLogRepository.save(LoginActivityLog.builder()
                    .userId(userId)
                    .userName(user != null ? user.getNetworkUsername() : null)
                    .role("NetworkAdmin")
                    .actionType("LOGOUT")
                    .description("Network admin logged out")
                    .timestamp(LocalDateTime.now())
                    .build());
        }

        session.invalidate();
        return true;

    }

    @Override
    public AdminUserDto addAdmin(AdminUserDto adminuserDto) {
        AdminUser adminuser = new AdminUser();
        adminuser.setAdminId(adminuserDto.getAdminId());
        adminuser.setAdminName(adminuserDto.getAdminName());
        String encryptedPassword = passwordEncoder.encode(adminuserDto.getAdminPassword());

        adminuser.setAdminPassword(encryptedPassword);
        adminUserRepository.save(adminuser);

        return new AdminUserDto(adminuser.getAdminId(), adminuser.getAdminName(),adminuser.getAdminPassword());
    }
}
