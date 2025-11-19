package com.nip.public_issue.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nip.public_issue.dtos.CreateUserDTO;
import com.nip.public_issue.models.department.Department;
import com.nip.public_issue.models.user.Role;
import com.nip.public_issue.models.user.User;
import com.nip.public_issue.repository.UserRepo;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    @Autowired
    private DepartmentService deptService;

    public User getUserById(Long id) {
        // Implementation to retrieve user by ID

        try {
            return userRepo.getReferenceById(id);
        } catch (Exception e) {
            throw new RuntimeException("User not found with id: " + id);
        }
    }




    public User createUser(CreateUserDTO userDTO) {
        // Implementation to create a new user

        User user = new User();

        user.setEmail(userDTO.getEmail());
        user.setName(userDTO.getName());
        user.setPassword(userDTO.getPassword());
        
        if(userDTO.getRole()==Role.OFFICE&&userDTO.getDepartmentId()!=null){
            Department dept = deptService.getDepartmentById(userDTO.getDepartmentId());
            user.setDepartment(dept);
        }


        User savedUser = userRepo.save(user);
        // System.out.println(savedUser.getDepartment().getName());
        return savedUser;
    }
}
