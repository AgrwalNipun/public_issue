package com.nip.public_issue.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nip.public_issue.models.user.User;
import com.nip.public_issue.repository.UserRepo;

@Service
public class UserService {

    @Autowired
    private UserRepo userRepo;

    public User getUserById(Long id) {
        // Implementation to retrieve user by ID

        try {
            return userRepo.getReferenceById(id);
        } catch (Exception e) {
            throw new RuntimeException("User not found with id: " + id);
        }
    }




    public User createUser(User user) {
        // Implementation to create a new user
        User savedUser = userRepo.save(user);
        return savedUser;
    }
}
