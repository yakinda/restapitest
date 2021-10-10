package com.example.demo.services;

import com.example.demo.models.User;
import org.springframework.stereotype.Service;

@Service
public class UserServices {
    public boolean validateUser(User user) {
        return true;
    }
}
