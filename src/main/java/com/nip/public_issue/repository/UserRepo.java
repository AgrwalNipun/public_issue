package com.nip.public_issue.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

import com.nip.public_issue.models.user.Role;
import com.nip.public_issue.models.user.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {

    Optional<User> findFirstByRole(Role role);

}
