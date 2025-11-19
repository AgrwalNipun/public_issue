package com.nip.public_issue.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nip.public_issue.Service.UserService;
import com.nip.public_issue.dtos.CreateUserDTO;
import com.nip.public_issue.models.user.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    UserService service;


    @PostMapping("/add")
    public ResponseEntity<User> addUser(@RequestBody CreateUserDTO entity) {
        


        User savedUser = service.createUser(entity);
        return ResponseEntity.ok(savedUser);
    }
    

  @GetMapping("/get")
public ResponseEntity<User> getUser(@RequestParam Long id) {
    System.out.println("Herreee with id"+id);
    return ResponseEntity.ok(service.getUserById(id));
}


}
