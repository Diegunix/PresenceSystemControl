package com.presencesystem.service;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import com.presencesystem.dao.domain.User;

public interface UserService {

    User save(User user);

    void delete(User user);

    User findOne(Long id);

    Iterable<User> findAll();

    Page<User> findByName(String firstName, PageRequest pageRequest);
    
    User findByFingerprint(String fingerprint);


}