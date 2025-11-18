package com.nip.public_issue.dtos;

import com.nip.public_issue.models.issue.Status;

import lombok.Getter;
import lombok.Setter;




@Getter
@Setter
public class UpdateStatusDTO {
    private long id;
    private Status status;   
}
