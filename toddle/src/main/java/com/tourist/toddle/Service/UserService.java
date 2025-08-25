package com.tourist.toddle.Service;

import com.tourist.toddle.Models.User;

public interface UserService {
    public User regUser(User user);

    public User findUserById(Integer id) throws Exception;
    public User findUserByEmail(String email);


    public User followUser(Integer id1, Integer id2) throws Exception;

    public User updUser(User user, Integer id) throws Exception;

    public java.util.List<User> searchUser(String uName);

    public User findUserByJwt(String jwt);
}