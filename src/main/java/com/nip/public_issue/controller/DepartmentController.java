package com.nip.public_issue.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nip.public_issue.Service.DepartmentService;
import com.nip.public_issue.dtos.CreateDepartmentDTO;
import com.nip.public_issue.models.department.Department;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;



@RequestMapping("/dept")
@RestController
public class DepartmentController {
    
    @Autowired
    private DepartmentService departmentService;

    @PostMapping("/add")
    public ResponseEntity<Department> postMethodName(@RequestBody CreateDepartmentDTO entity) {

        
        return  ResponseEntity.ok(departmentService.createDepartment(entity));
    }
    

    @GetMapping("/all")
    public ResponseEntity<List<Department>> getAllDepartment(

    ) {
        return ResponseEntity.ok(departmentService.getAllDepartment());
    }
    

}
