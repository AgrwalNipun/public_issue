package com.nip.public_issue.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class IssueResponseDTO {

    private Long id;               // issue id
    private Long reporterId;
    private String reporterName;

    private Long departmentId;
    private String departmentName;

    private String message;
    private String location;
    private String imageTag;

    private String status;
    private String createdAt;
    private String updatedAt;
}
