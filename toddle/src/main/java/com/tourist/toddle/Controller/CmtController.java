package com.tourist.toddle.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import com.tourist.toddle.Models.Cmt;
import com.tourist.toddle.Models.User;
import com.tourist.toddle.Service.CmtService;
import com.tourist.toddle.Service.UserService;

@RestController
public class CmtController {
    @Autowired
    private CmtService cmtService;

    @Autowired
    private UserService userService;

    @PostMapping("/api/cmt/post/{pid}")
    public Cmt newCmt(@RequestBody Cmt cmt, 
                      @RequestHeader("Authorization") String jwt,
                      @PathVariable Integer pid) throws Exception{

        User user = userService.findUserByJwt(jwt);

        Cmt newCmt = cmtService.newCmt(cmt, pid, user.getId());
        return newCmt;
    }

    @PutMapping("/api/cmt/like/{cid}")
    public Cmt cmtLiked(@RequestHeader("Authorization") String jwt,
                        @PathVariable Integer cid) throws Exception{

        User user = userService.findUserByJwt(jwt);

        Cmt likedCmt = cmtService.likedCmt(cid, user.getId());
        return likedCmt;
    }
}
