package com.nip.public_issue.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nip.public_issue.dtos.CreateDepartmentDTO;
import com.nip.public_issue.models.department.Department;
import com.nip.public_issue.repository.DepartmentRepo;

@Service
public class DepartmentService {
    

    @Autowired
    private DepartmentRepo departmentRepo;


    public Department createDepartment(CreateDepartmentDTO enitity){

        Department department =new Department();
        department.setCategory(enitity.getCategory());
        department.setName(enitity.getName());

            return departmentRepo.save(department);
    }

    public Department getDepartmentById(Long id){
        return departmentRepo.getReferenceById(id);
    }


    public List<Department> getAllDepartment(){
        return departmentRepo.findAll();
    }


}
