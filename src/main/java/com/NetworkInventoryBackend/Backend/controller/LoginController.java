package com.NetworkInventoryBackend.Backend.controller;

import com.NetworkInventoryBackend.Backend.dto.AdminUserDto;
import com.NetworkInventoryBackend.Backend.dto.NetworkAdminDto;
import com.NetworkInventoryBackend.Backend.dto.UserDto;
import com.NetworkInventoryBackend.Backend.service.AdminUserServiceImpl;
import com.NetworkInventoryBackend.Backend.service.NetworkAdminServiceImpl;
import com.NetworkInventoryBackend.Backend.service.UserServiceImpl;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping
public class LoginController {

    @Autowired
    private UserServiceImpl userServiceImpl;
    @Autowired
    private AdminUserServiceImpl adminUserServiceImpl;

    @Autowired
    private NetworkAdminServiceImpl networkAdminServiceImpl;

    @PostMapping("/networkadmin-creation")
    public NetworkAdminDto createNetworkAdmin(@RequestBody NetworkAdminDto networkAdminDto)
    {
        return networkAdminServiceImpl.createNetworkAdmin(networkAdminDto);
    }

    @PostMapping("/networkadmin")
    public Boolean loginNetworkAdmin(@RequestBody NetworkAdminDto networkAdminDto, HttpSession session)
    {
        return networkAdminServiceImpl.networkAdminLogin(networkAdminDto, session);
    }

    @PostMapping("/user-creation")
    public UserDto createUsers(@RequestBody UserDto userDto)
    {
        return userServiceImpl.createUser(userDto);
    }
    @PostMapping("/admin-creation")
    public AdminUserDto createAdmin(@RequestBody AdminUserDto adminUserDto)
    {
        return adminUserServiceImpl.createAdmin(adminUserDto);
    }
    @PostMapping("/user")
    public Boolean loginUser(@RequestBody UserDto userDto, HttpSession session) {
        return userServiceImpl.userLogin(userDto, session);
    }

    @PostMapping("/admin")
    public Boolean loginAdmin(@RequestBody AdminUserDto adminUserDto, HttpSession session) {
        return adminUserServiceImpl.adminUserLogin(adminUserDto, session);
    }


    @PostMapping("/logout")
    public Boolean logout(HttpSession session) {
        String role = (String) session.getAttribute("role");

        if ("NetworkAdmin".equalsIgnoreCase(role)) {
            return networkAdminServiceImpl.logout(session);
        } else if ("Admin".equalsIgnoreCase(role)) {
            return adminUserServiceImpl.logout(session);
        } else if ("User".equalsIgnoreCase(role)) {
            return userServiceImpl.logout(session);
        }
        // already logged out or invalid session
        return false;

    }

    @PostMapping("/add-admin")
    public AdminUserDto addAdmins( @RequestBody  AdminUserDto adminUserDto)
    {
        return networkAdminServiceImpl.addAdmin(adminUserDto);
    }


}
