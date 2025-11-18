package com.nip.public_issue.models;

import lombok.*;
// import lombok.Getter;

@Data
@Getter
@Setter
public class EmailDetails {
    private String recipient;
    private String msgBody;
    private String subject;
    private String attachment;
}
