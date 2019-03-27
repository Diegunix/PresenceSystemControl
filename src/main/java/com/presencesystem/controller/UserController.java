package com.presencesystem.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.presencesystem.controller.dto.UserDTO;
import com.presencesystem.controller.dto.mappers.UserMapper;
import com.presencesystem.dao.domain.User;
import com.presencesystem.service.UserService;

@RestController
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @ResponseBody
    @GetMapping(value = "/users")
    public ResponseEntity<Iterable<UserDTO>> getAllUser() {
        Iterable<UserDTO> result = UserMapper.INSTANCE.map(userService.findAll());
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ResponseBody
    @PostMapping(value = "/user")
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO dto) {
        UserDTO result = UserMapper.INSTANCE.map(userService.save(UserMapper.INSTANCE.map(dto)));
        return new ResponseEntity<>(result, HttpStatus.CREATED);
    }

    @ResponseBody
    @PutMapping(value = "/user/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable("id") Long id, @RequestBody UserDTO dto) {
        dto.setId(id);
        UserDTO result = UserMapper.INSTANCE.map(userService.save(UserMapper.INSTANCE.map(dto)));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ResponseBody
    @DeleteMapping(value = "/user/{id}")
    public ResponseEntity<UserDTO> deleteUser(@PathVariable("id") Long id) {
        User user = userService.findOne(id);
        userService.delete(user);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @ResponseBody
    @GetMapping(value = "/user/{id}")
    public ResponseEntity<UserDTO> getOne(@PathVariable("id") Long id) {
        UserDTO result = UserMapper.INSTANCE.map(userService.findOne(id));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @ResponseBody
    @GetMapping(value = "/user/{fingerprint}")
    public ResponseEntity<UserDTO> getOneByFingerprint(@PathVariable("fingerprint") String fingerprint) {
        UserDTO result = UserMapper.INSTANCE.map(userService.findByFingerprint(fingerprint));
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

}