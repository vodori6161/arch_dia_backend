package com.NetworkInventoryBackend.Backend.service;

import com.NetworkInventoryBackend.Backend.dto.UserDto;
import com.NetworkInventoryBackend.Backend.model.User;
import com.NetworkInventoryBackend.Backend.repository.UserRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.UUID;
@Service
public class UserServiceImpl implements  UserService {

    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
    @Autowired
    private UserRepository userRepository;


    @Override
    public UserDto createUser(UserDto userDto) {

        User user = new User();
        user.setUserId(UUID.randomUUID().toString());
        user.setUserName(userDto.getUserName());

        // Encrypt the password
        String encryptedPassword = passwordEncoder.encode(userDto.getUserPassword());
        user.setUserPasword(encryptedPassword);

        userRepository.save(user);


        // Save user to database (you'll need to inject and use UserRepository here)

        return new UserDto(user.getUserId(), user.getUserName(), encryptedPassword);
    }
    public Boolean userLogin(UserDto userDto, HttpSession session) {
        User user = userRepository.findByUserName(userDto.getUserName());
        if (user != null && passwordEncoder.matches(userDto.getUserPassword(), user.getUserPasword())) {
            session.setAttribute("userId", user.getUserId());
            session.setAttribute("role", "USER");
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
