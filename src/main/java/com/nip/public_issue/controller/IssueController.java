package com.nip.public_issue.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nip.public_issue.Service.IssueService;
import com.nip.public_issue.dtos.CreateIssueDTO;
import com.nip.public_issue.dtos.IssueResponseDTO;
import com.nip.public_issue.dtos.UpdateStatusDTO;
import com.nip.public_issue.models.issue.Issue;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/issues")
public class IssueController {

    @Autowired
    private IssueService issueService;

    @PostMapping("/add")
    public IssueResponseDTO createIssue(@RequestBody CreateIssueDTO dto) {
        return issueService.createIssue(dto);
    }


    @PostMapping("/updateStatus")
    public Issue postMethodName(@RequestBody UpdateStatusDTO statusDTO ) {
        
        return issueService.updateIssueStatus(statusDTO.getId(),statusDTO.getStatus());
    }



}
