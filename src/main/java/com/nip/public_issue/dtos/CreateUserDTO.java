package com.nip.public_issue.dtos;


import com.nip.public_issue.models.user.Role;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserDTO {

    private String name;
    private String email;
    private String password;
    private Role role;
    private Long departmentId;
    
}
