package com.tourist.toddle.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tourist.toddle.Models.Post;
import com.tourist.toddle.Models.User;
import com.tourist.toddle.Repository.PostRepo;
import com.tourist.toddle.Repository.UserRepo;

@Service
public class PostServiceImpl implements PostService{

    @Autowired
    PostRepo postRepo;

    @Autowired
    UserService userService;

    @Autowired
    UserRepo userRepo;

    @Override
    public Post newPost(Post post, Integer id) throws Exception {
        Post nPost = new Post();
        nPost.setCaption(post.getCaption());
        nPost.setImage(post.getImage());
        nPost.setLdt(LocalDateTime.now());
        nPost.setVideo(post.getVideo());
        nPost.setUser(userService.findUserById(id));

        return postRepo.save(nPost);
    }

    @Override
    public String delPost(Integer pid, Integer uid) throws Exception {
        Post post = findPostByPid(pid);
        User user = userService.findUserById(uid);

        if(post.getUser().getId() != user.getId()){
            throw new Exception("Not authorized to delete another account's post.");
        }

        postRepo.delete(post);

        return "Post Deleted";
    }

    @Override
    public List<Post> findPostByUid(Integer uid) {
        return postRepo.findPostByUserId(uid);
    }

    @Override
    public Post findPostByPid(Integer pid) throws Exception {
        Optional<Post> otp = postRepo.findById(pid);
        if(otp.isEmpty()){
            throw new Exception("No post with id: " + pid);
        }
        
        return otp.get();
    }

    @Override
    public List<Post> findAll() {
        return postRepo.findAll();
    }

    @Override
    public Post savedPost(Integer pid, Integer uid) throws Exception {
        Post post = findPostByPid(pid);
        User user = userService.findUserById(uid);
     
        if(user.getSavedPosts().contains(post)){
            user.getSavedPosts().remove(post);
        }else{
            user.getSavedPosts().add(post);
        }

        userRepo.save(user);
        return post;
    }

    @Override
    public Post postLike(Integer pid, Integer uid) throws Exception {
        Post post = findPostByPid(pid);
        User user = userService.findUserById(uid);

        if(post.getLikes().contains(user)){
            post.getLikes().remove(user);
        }else{
            post.getLikes().add(user);
        }

        return postRepo.save(post);
    }

}
