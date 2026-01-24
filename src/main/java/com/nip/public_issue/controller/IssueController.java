package com.nip.public_issue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nip.public_issue.Service.IssueService;
import com.nip.public_issue.Service.UserService;
import com.nip.public_issue.dtos.CreateIssueDTO;
import com.nip.public_issue.dtos.IssueResponseDTO;
import com.nip.public_issue.dtos.UpdateStatusDTO;
import com.nip.public_issue.models.department.Department;
import com.nip.public_issue.models.issue.Issue;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/issues")
public class IssueController {

    @Autowired
    private IssueService issueService;

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public IssueResponseDTO createIssue(@RequestBody CreateIssueDTO dto) {
        return issueService.createIssue(dto);
    }


    @PostMapping("/updateStatus")
    public Issue postMethodName(@RequestBody UpdateStatusDTO statusDTO ) {
        long userId = statusDTO.getUserId();

        
        Issue issue = issueService.getIssueById(statusDTO.getId());

        Department dept = issue.getDepartment();
        
        
        if(dept.getUsers().contains(userService.getUserById(userId))){
        return issueService.updateIssueStatus(statusDTO.getId(),statusDTO.getStatus());
        }
        else{
            throw new IllegalArgumentException("User is not eligible to update status");
        }
    }
}
