package com.tourist.toddle.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.tourist.toddle.Models.Post;
import com.tourist.toddle.Models.User;
import com.tourist.toddle.Response.ResponseApi;
import com.tourist.toddle.Service.PostService;
import com.tourist.toddle.Service.UserService;

import org.springframework.web.bind.annotation.PutMapping;



@RestController
public class PostController {
    
    @Autowired
    PostService postService;

    @Autowired
    UserService userService;

    @PostMapping("/api/post")
    public ResponseEntity<Post> createPost(@RequestBody Post post,
                                           @RequestHeader("Authorization") String jwt) 
                                throws Exception{
        
        User userReq = userService.findUserByJwt(jwt); 
        Post cPost = postService.newPost(post, userReq.getId());
        
        return new ResponseEntity<>(cPost, HttpStatus.ACCEPTED);
    }

    @DeleteMapping("/api/delete/post/{pid}")
    public ResponseEntity<ResponseApi> delPost(@PathVariable Integer pid, 
                                               @RequestHeader("Authorization") String jwt) 
                                throws Exception{
        
        User userReq = userService.findUserByJwt(jwt);
        String mes = postService.delPost(pid, userReq.getId());
        ResponseApi resp = new ResponseApi(mes, true);
        return new ResponseEntity<ResponseApi>(resp, HttpStatus.OK);
    }

    @GetMapping("/api/post/{pid}")
    public ResponseEntity<Post> findPostByIdHandlerEntity(@PathVariable Integer pid) throws Exception{

        Post post = postService.findPostByPid(pid);   
        return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
    }

    @GetMapping("/api/post/user/{uid}")
    public ResponseEntity<List<Post>> findUserPostEntity(@PathVariable Integer uid){
        List<Post> post = postService.findPostByUid(uid);
        return new ResponseEntity<List<Post>>(post, HttpStatus.OK);        
    }

    @GetMapping("/api/post")
    public ResponseEntity<List<Post>> findAllPost() {
        List<Post> post = postService.findAll();
        return new ResponseEntity<List<Post>>(post, HttpStatus.OK);
    }
    
    @PutMapping("/api/post/save/{pid}/user/{uid}")
    public ResponseEntity<Post> savedPostHandlerEntity(@PathVariable Integer pid, @PathVariable Integer uid) throws Exception{

        Post post = postService.savedPost(pid, uid);   
        return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
    }

    @PutMapping("/api/post/like/{pid}")
    public ResponseEntity<Post> likePostHandlerEntity(@PathVariable Integer pid,
                                                      @RequestHeader("Authorization") String jwt) 
                                throws Exception{
        
        User userReq = userService.findUserByJwt(jwt);                                    
        Post post = postService.postLike(pid, userReq.getId());   
        return new ResponseEntity<Post>(post, HttpStatus.ACCEPTED);
    }
}
