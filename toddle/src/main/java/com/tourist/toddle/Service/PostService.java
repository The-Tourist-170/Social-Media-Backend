package com.tourist.toddle.Service;

import java.util.List;
import com.tourist.toddle.Models.Post;

public interface PostService {
    Post newPost(Post post, Integer id) throws Exception;

    String delPost(Integer pid, Integer uid) throws Exception;

    List<Post> findPostByUid(Integer uid);

    Post findPostByPid(Integer pid) throws Exception;

    List<Post> findAll();

    Post savedPost(Integer pid, Integer uid) throws Exception;

    Post postLike(Integer pid, Integer uid) throws Exception;
}
