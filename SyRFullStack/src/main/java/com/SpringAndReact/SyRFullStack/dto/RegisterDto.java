package com.SpringAndReact.SyRFullStack.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor
public class RegisterDto {
    private String name;
    private  String username;
    private  String email;
    private String password;
}
