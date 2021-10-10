package com.example.demo.controllers;

import com.example.demo.models.Constants;
import com.example.demo.models.ResponseObject;
import com.example.demo.models.User;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "api/users")
public class UserController {
    @Autowired
    UserRepository userRepository;
    @Autowired
    UserServices userServices;

    @GetMapping("")
    ResponseEntity<ResponseObject> getAllUser() {

        List<User> users = userRepository.findAll();
        ResponseObject rs = new ResponseObject(Constants.STATUS_OK, "Success", users);
        return ResponseEntity.status(HttpStatus.OK).body(rs);
    }

    @GetMapping("/{id}")
    ResponseEntity<ResponseObject> getUserById(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            ResponseObject rs = new ResponseObject(Constants.STATUS_OK, "Success", user);
            return ResponseEntity.status(HttpStatus.OK).body(rs);
        }
        ResponseObject rsf = new ResponseObject(Constants.STATUS_FAIL, "fail", null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rsf);
    }

    @PostMapping("/create")
    ResponseEntity<ResponseObject> createUser(@RequestBody User user) {
        ResponseObject rs;
        if (userServices.validateUser(user)) {
            userRepository.save(user);
            rs = new ResponseObject(Constants.STATUS_OK, "Create user success", user);
            return ResponseEntity.status(HttpStatus.OK).body(rs);
        }
        rs = new ResponseObject(Constants.STATUS_FAIL, "Create user failed,user isn't validate", "");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(rs);
    }

    @PutMapping("/{id}")
    ResponseEntity<ResponseObject> editUserById(@PathVariable Long id, @RequestBody User user) {
        ResponseObject rs;
        User userFind = userRepository.getById(id);
        if (userFind != null) {
            if (userServices.validateUser(user)) {

                userFind.setUsername(user.getUsername());
                userFind.setPassword(user.getPassword());
                userRepository.save(userFind);
                rs = new ResponseObject(Constants.STATUS_OK, "Edit Success", userFind);
                return ResponseEntity.status(HttpStatus.OK).body(rs);
            }
            rs = new ResponseObject(Constants.STATUS_FAIL, "Edit Fail,User not validate", null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(rs);
        }
        rs = new ResponseObject(Constants.STATUS_FAIL, "Edit Fail,User isn't exist", null);
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(rs);
    }

    @DeleteMapping("/{id}")
    ResponseEntity<ResponseObject> deleteUserById(@PathVariable Long id) {
        ResponseObject responseRs;
        if (userRepository.existsById(id)) {
            responseRs = new ResponseObject(Constants.STATUS_OK, "Delete Success", null);
            return ResponseEntity.status(HttpStatus.OK).body(responseRs);
        }
        responseRs = new ResponseObject(Constants.STATUS_FAIL, "User not exist", null);
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(responseRs);
    }
}
