package com.presencesystem.service.impl;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.presencesystem.dao.domain.User;
import com.presencesystem.dao.repository.UserRepository;
import com.presencesystem.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {
        return userRepository.save(user);
    }

    public void delete(User user) {
        userRepository.delete(user);
    }

    public User findOne(Long id) {
        Optional<User> optUser = userRepository.findById(id);
        if (optUser.isPresent()) {
            return optUser.get();
        }
        throw new EntityNotFoundException();
    }

    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    public Page<User> findByName(String firstName, PageRequest pageRequest) {
        return userRepository.findByFirstName(firstName, pageRequest);
    }

    public User findByFingerprint(String fingerprint) {
        return userRepository.findByFingerprint(fingerprint);
    }

}