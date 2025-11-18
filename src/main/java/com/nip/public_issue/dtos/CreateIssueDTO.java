package com.nip.public_issue.dtos;

import lombok.Getter;
import lombok.Setter;



@Getter
@Setter
public class CreateIssueDTO {

    Long reporterId;
    Long departmentId;

    String message;
    String location;
    String imageTag;

}
