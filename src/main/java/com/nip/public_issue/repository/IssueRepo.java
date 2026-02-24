package com.nip.public_issue.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.nip.public_issue.models.issue.Issue;
import com.nip.public_issue.models.issue.Status;

@Repository
public interface IssueRepo extends JpaRepository<Issue, Long> {

    @Query("SELECT i FROM Issue i WHERE i.status != :status AND i.createdAt < :date ")
    List<Issue> findIncompleteIssuesBeforeDate(
            @Param("status") Status status,
            @Param("date") LocalDateTime date
        );
}
