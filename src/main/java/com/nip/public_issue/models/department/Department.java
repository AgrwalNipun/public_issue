package com.nip.public_issue.models.department;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.nip.public_issue.models.BaseEntity;
import com.nip.public_issue.models.Category;
import com.nip.public_issue.models.issue.Issue;
import com.nip.public_issue.models.user.User;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;



@Builder
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
// @Category
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Department extends BaseEntity {

    @Column(nullable = false)
    private String name;

    @Enumerated(EnumType.STRING)
    @Builder.Default()
    private Category category = Category.GENERAL;

    
    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL)
    @Builder.Default
    @JsonManagedReference
    private List<Issue> issues = new ArrayList<>();



    @OneToMany(mappedBy = "department",cascade = CascadeType.ALL)
    @Builder.Default
    @JsonManagedReference
    private List<User> users = new ArrayList<>();

}
