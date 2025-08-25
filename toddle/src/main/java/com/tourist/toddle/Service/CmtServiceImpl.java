package com.tourist.toddle.Service;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tourist.toddle.Models.Cmt;
import com.tourist.toddle.Models.Post;
import com.tourist.toddle.Models.User;
import com.tourist.toddle.Repository.CmtRepo;
import com.tourist.toddle.Repository.PostRepo;

@Service
public class CmtServiceImpl implements CmtService{

    @Autowired
    PostService postService;

    @Autowired
    UserService userService;

    @Autowired
    CmtRepo cmtRepo;

    @Autowired
    PostRepo postRepo;

    @Override
    public Cmt newCmt(Cmt cmt, Integer pid, Integer uid) throws Exception {
        User user = userService.findUserById(uid);
        Post post = postService.findPostByPid(pid);

        cmt.setUser(user);
        cmt.setCmt(cmt.getCmt());
        cmt.setCmtAt(LocalDateTime.now());

        Cmt cmtSaved = cmtRepo.save(cmt);

        post.getCmt().add(cmtSaved);

        postRepo.save(post);
        return cmtSaved;
        
    }

    @Override
    public Cmt findCmtById(Integer cmtId) throws Exception {
        Optional<Cmt> opCmt = cmtRepo.findById(cmtId);
        
        if(opCmt.isEmpty()){
            throw new Exception("Comment does not exist");
        }
        return opCmt.get();
    }

    @Override
    public Cmt likedCmt(Integer cmtId, Integer uid) throws Exception {

        Cmt cmt = findCmtById(cmtId);
        User user = userService.findUserById(uid);

        if(!cmt.getLikes().contains(user)){
            cmt.getLikes().add(user);
        } else {
            cmt.getLikes().remove(user);
        }
        return cmtRepo.save(cmt);
    }

}
