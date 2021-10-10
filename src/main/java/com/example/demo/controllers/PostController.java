package com.example.demo.controllers;

import com.example.demo.models.Constants;
import com.example.demo.models.Post;
import com.example.demo.models.ResponseObject;
import com.example.demo.repositories.PostRepository;
import com.example.demo.repositories.UserRepository;
import com.example.demo.services.PostServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(path = "/api/users")
public class PostController {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private PostServices postServices;
    @Autowired
    private UserRepository userRepository;

    @GetMapping("/posts")
    ResponseEntity<ResponseObject> getAllPost() {

        List<Post> posts = postRepository.findAll();
        ResponseObject rs = new ResponseObject(Constants.STATUS_OK, "Success", posts);
        return ResponseEntity.status(HttpStatus.OK).body(rs);
    }

    @GetMapping("/posts/{id}")
    ResponseEntity<ResponseObject> getPostById(@PathVariable Long id) {
        Optional<Post> post = postRepository.findById(id);
        if (post.isPresent()) {
            ResponseObject rs = new ResponseObject(Constants.STATUS_OK, "Success", post);
            return ResponseEntity.status(HttpStatus.OK).body(rs);
        }
        ResponseObject rsf = new ResponseObject(Constants.STATUS_FAIL, "fail", null);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(rsf);
    }

    @PostMapping("/{id}/createpost")
    ResponseEntity<ResponseObject> createPost(@PathVariable Long id, @RequestBody Post post) {
        ResponseObject rs;
        if (postServices.validatePost(post)) {
            post.setUser(userRepository.getById(id));
            postRepository.save(post);
            rs = new ResponseObject(Constants.STATUS_OK, "Create post success", post);
            return ResponseEntity.status(HttpStatus.OK).body(rs);
        }
        rs = new ResponseObject(Constants.STATUS_FAIL, "Create user failed,user isn't validate", "");
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(rs);
    }

//    @PutMapping("/{id}")
//    ResponseEntity<ResponseObject> editUserById(@PathVariable Long id, @RequestBody User user) {
//        ResponseObject rs;
//        User userFind = userRepository.getById(id);
//        if (userFind != null) {
//            if (userServices.validateUser(user)) {
//
//                userFind.setUsername(user.getUsername());
//                userFind.setPassword(user.getPassword());
//                userRepository.save(userFind);
//                rs = new ResponseObject(Constants.STATUS_OK, "Edit Success", userFind);
//                return ResponseEntity.status(HttpStatus.OK).body(rs);
//            }
//            rs = new ResponseObject(Constants.STATUS_FAIL, "Edit Fail,User not validate", null);
//            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(rs);
//        }
//        rs = new ResponseObject(Constants.STATUS_FAIL, "Edit Fail,User isn't exist", null);
//        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(rs);
//    }
//
//    @DeleteMapping("/{id}")
//    ResponseEntity<ResponseObject> deleteUserById(@PathVariable Long id) {
//        ResponseObject responseRs;
//        if (userRepository.existsById(id)) {
//            responseRs = new ResponseObject(Constants.STATUS_OK, "Delete Success", null);
//            return ResponseEntity.status(HttpStatus.OK).body(responseRs);
//        }
//        responseRs = new ResponseObject(Constants.STATUS_FAIL, "User not exist", null);
//        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(responseRs);
//    }
}
