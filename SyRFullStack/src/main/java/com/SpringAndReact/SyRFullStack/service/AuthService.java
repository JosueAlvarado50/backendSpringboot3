package com.SpringAndReact.SyRFullStack.service;

import com.SpringAndReact.SyRFullStack.dto.JwtAuthResponse;
import com.SpringAndReact.SyRFullStack.dto.LoginDto;
import com.SpringAndReact.SyRFullStack.dto.RegisterDto;
import org.springframework.stereotype.Service;

@Service
public interface AuthService {
    String register(RegisterDto registerDto);
    JwtAuthResponse login(LoginDto loginDto);
}
