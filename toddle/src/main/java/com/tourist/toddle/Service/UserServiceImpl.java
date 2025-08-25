package com.tourist.toddle.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tourist.toddle.Config.JwtPro;
import com.tourist.toddle.Models.User;
import com.tourist.toddle.Repository.UserRepo;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepo userRepo;

    @Override
    public User regUser(User user) {
        User nUser = new User();
        nUser.setEmail(user.getEmail());
        nUser.setId(user.getId());
        nUser.setPass(user.getPass());
        nUser.setfName(user.getfName());
        nUser.setlName(user.getlName());

        User savedUser = userRepo.save(nUser);

        return savedUser;
    }

    @Override
    public User findUserById(Integer id) throws Exception {
        Optional<User> user = userRepo.findById(id);
        if(user.isPresent()){
            return user.get();
        }

        throw new Exception("User does not exist with id: " + id);
    }

    @Override
    public User findUserByEmail(String email) {
        User user = userRepo.findByEmail(email);
        return user;
    }

    @Override
    public User followUser(Integer userReq, Integer user) throws Exception {
        User userReqt = findUserById(userReq);
        User u = findUserById(user);

        u.getFollowers().add(userReqt.getId());
        userReqt.getFollowings().add(u.getId());

        userRepo.save(userReqt);
        userRepo.save(u);

        return userReqt;
    }

    @Override
    public User updUser(User user, Integer id) throws Exception {
        Optional<User> u1 = userRepo.findById(id); 

        User lastUser = u1.get();
         

        if(u1.isEmpty()){
            throw new Exception("User does not exist with id: " + id);
        }

        if(user.getEmail() != null){
            lastUser.setEmail(user.getEmail());
        }

        if(user.getId() != null){
            lastUser.setId(user.getId());
        }

        if(user.getPass() != null){
            lastUser.setPass(user.getPass());
        }

        if(user.getfName() != null){
            lastUser.setfName(user.getfName());
        }

        if(user.getlName() != null){
            lastUser.setlName(user.getlName());
        }
        if(user.getGender() != null){
            lastUser.setGender(user.getGender());
        }
        userRepo.save(lastUser);
        return lastUser;
    }

    @Override
    public List<User> searchUser(String uName) {
        return userRepo.searchUser(uName);
    }

    @Override
    public User findUserByJwt(String jwt) {
        String email = JwtPro.getEmailFromJwt(jwt);
        User user = userRepo.findByEmail(email);
        return user;
    }   
}
