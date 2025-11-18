package com.nip.public_issue.Service;

import org.hibernate.validator.cfg.defs.EmailDef;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.nip.public_issue.dtos.CreateIssueDTO;
import com.nip.public_issue.dtos.IssueResponseDTO;
import com.nip.public_issue.models.EmailDetails;
import com.nip.public_issue.models.department.Department;
import com.nip.public_issue.models.issue.Issue;
import com.nip.public_issue.models.issue.Status;
import com.nip.public_issue.models.user.User;
import com.nip.public_issue.repository.IssueRepo;


@Service
public class IssueService {

    @Autowired
    private IssueRepo issueRepo;

    @Autowired
    private UserService userService;

    @Autowired 
    private DepartmentService deptService;

    @Autowired
    private EmailService emailService;


public IssueResponseDTO createIssue(CreateIssueDTO dto){

    User reporter = userService.getUserById(dto.getReporterId());
    Department dept = deptService.getDepartmentById(dto.getDepartmentId());

    Issue issue = new Issue();
    issue.setReporter(reporter);
    issue.setDepartment(dept);
    issue.setMessage(dto.getMessage());
    issue.setLocation(dto.getLocation());
    issue.setImageTag(dto.getImageTag());
    issue.setStatus(Status.PENDING);

    Issue saved = issueRepo.save(issue);

    // Build Response DTO
    IssueResponseDTO res = new IssueResponseDTO();
    res.setId(saved.getId());
    
    res.setReporterId(reporter.getId());
    res.setReporterName(reporter.getName());

    res.setDepartmentId(dept.getId());
    res.setDepartmentName(dept.getName());

    res.setMessage(saved.getMessage());
    res.setLocation(saved.getLocation());
    res.setImageTag(saved.getImageTag());
    
    res.setStatus(saved.getStatus().toString());
    res.setCreatedAt(saved.getCreatedAt().toString());
    res.setUpdatedAt(saved.getUpdatedAt().toString());

    return res;
}





public Issue updateIssueStatus(Long id, Status status) {

    Issue issue = issueRepo.findById(id)
                    .orElseThrow(() -> new RuntimeException("Issue not found"));

    // System.out.println();

    if(issue.getStatus()==status){}

    else{
        EmailDetails emailDetails= new EmailDetails();

        emailDetails.setMsgBody("Update!\n\n The status of your issue with id : "+id+" has been updated to : "+status);
        emailDetails.setRecipient(issue.getReporter().getEmail());
        emailDetails.setSubject("Status Update");

        String sol = emailService.sendSimpleMail(emailDetails);  
        System.out.println(sol+"/////////////");
    }


    issue.setStatus(status);




    return issueRepo.save(issue);
}

        


}
