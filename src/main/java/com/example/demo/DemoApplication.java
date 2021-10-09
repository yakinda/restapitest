package com.example.demo;

import com.example.demo.models.Post;
import com.example.demo.models.User;
import com.example.demo.repositories.CommentRepository;
import com.example.demo.repositories.PostRepository;
import com.example.demo.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@RequiredArgsConstructor
public class DemoApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(DemoApplication.class, args);
    }

    private final UserRepository userRepo;
    private final PostRepository postRepo;
    private final CommentRepository commentRepo;

    @Override
    public void run(String... args) throws Exception {
//        User user1 = new User();
//        user1.setUsername("thanh");
//
//        Post post1 = new Post();
//        post1.setTitle("fdsaf");
//        post1.setContent("fdsaffd");
//        post1.setUser(user1);
//
//        Post post2 = new Post();
//        post2.setTitle("fdsaffdsa2");
//        post2.setContent("fdsaffdfsdaf23");
//        post2.setUser(user1);
//
//        Comment cmt1 = new Comment();
//        cmt1.setContent("FAFA");
//        cmt1.setPost(post2);
//
//        Comment cmt2 = new Comment();
//        cmt2.setContent("FAFA2");
//        cmt2.setPost(post2);
//
//        Comment cmt3 = new Comment();
//        cmt3.setContent("FAfsadfFA3");
//        cmt3.setPost(post1);
//
//        List<Comment> comments1 = new ArrayList<>();
//        comments1.add(cmt3);
//        post1.setComments(comments1);
//
//        List<Comment> comments2 = new ArrayList<>();
//        comments2.add(cmt1);
//        comments2.add(cmt2);
//        post2.setComments(comments2);
//
//        List<Post> posts = new ArrayList<>();
//        posts.add(post1);
//        posts.add(post2);
//
//        user1.setPosts(posts);
//        userRepo.saveAndFlush(user1);
//        postRepo.saveAndFlush(post1);
//        postRepo.saveAndFlush(post2);
//        commentRepo.saveAndFlush(cmt1);
//        commentRepo.saveAndFlush(cmt2);
//        commentRepo.saveAndFlush(cmt3);

//        User user = userRepo.getById(14L);
//        System.out.println(user);
//        List<Post> posts = user.getPosts();
//        for (Post post : posts
//        ) {
//            System.out.println(post.toString());
//        }
        User user1 = new User();
        user1.setUsername("thanh");
//        userRepo.save(user1);
//        User user1 = userRepo.findById(21L).orElse(null);
        Post post5 = new Post();
        post5.setTitle("jlksdahf");
        post5.setUser(user1);
        postRepo.saveAndFlush(post5);


    }
}
