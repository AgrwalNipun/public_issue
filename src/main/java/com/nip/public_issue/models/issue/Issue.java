package com.nip.public_issue.models.issue;

import com.fasterxml.jackson.annotation.JsonBackReference;

// import java.util.Locale.Category;

import com.nip.public_issue.models.BaseEntity;
import com.nip.public_issue.models.Category;
import com.nip.public_issue.models.department.Department;
import com.nip.public_issue.models.user.User;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;



@Entity
@Setter @Getter 
@NoArgsConstructor @AllArgsConstructor
@Builder
public class Issue extends BaseEntity{
    



    // @Column(nullable = false)

    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private User reporter;


    @ManyToOne(fetch = FetchType.LAZY)
    @JsonBackReference
    private Department department;

    private String message;
    private String location;
    private String imageTag;




    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Category issueCategory = Category.GENERAL;


@Enumerated(EnumType.STRING)
    @Builder.Default
    private Status status = Status.PENDING;


}
