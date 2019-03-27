package com.presencesystem.dao.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.presencesystem.dao.domain.User;

public interface UserRepository extends JpaRepository<User, Long> {

    Page<User> findByFirstName(String firstName, Pageable pageable);

    User findByFingerprint(String fingerprint);

}