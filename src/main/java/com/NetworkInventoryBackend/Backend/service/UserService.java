package com.NetworkInventoryBackend.Backend.service;

import com.NetworkInventoryBackend.Backend.dto.UserDto;
import jakarta.servlet.http.HttpSession;

public interface UserService {
    public UserDto createUser(UserDto userDto);
    public Boolean userLogin(UserDto userDto, HttpSession session);
    public Boolean logout(HttpSession session);
}
