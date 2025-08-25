package com.tourist.toddle.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.tourist.toddle.Models.User;
import com.tourist.toddle.Repository.UserRepo;
import com.tourist.toddle.Service.UserService;

@RestController
public class UserController {

    @Autowired
    UserRepo userRepo;

    @Autowired
    UserService userService;

    @GetMapping("/api/user")
    public List<User> getUsers(){
        List<User> users = userRepo.findAll();
        
        return users;
    }

    @GetMapping("/api/user/{id}")
    public User getUserById(@PathVariable("id") Integer id) throws Exception{
        User user = userService.findUserById(id);
        return user;
    }

    @PutMapping("/api/user")
    public User updateUser(@RequestHeader("Authorization") String jwt, @RequestBody User user) throws Exception{
        
        User userReq = userService.findUserByJwt(jwt);
        User updUser = userService.updUser(user, userReq.getId());
        return updUser;
    }

    @PutMapping("/api/user/follow/{id2}")
    public User followhandler(@RequestHeader("Authorization") String jwt, @PathVariable Integer id) throws Exception{
        User userReq = userService.findUserByJwt(jwt);
        User user = userService.followUser(userReq.getId(), id);
        return user;
    }

    // @DeleteMapping("user/{userId}")
	// public String deleteUser(@PathVariable("userId") Integer userId) throws Exception {

	// 	java.util.Optional<User> user = userRepo.findById(userId);
		
	// 	if(user.isEmpty()) {
	// 		throw new Exception("user not exit with id "+userId);
	// 	}
		
	// 	userRepo.delete(user.get());
		
	// 	return "user deleted successfully with id "+userId;
	// }

    @GetMapping("/api/user/search")
    public List<User> searchUser(@RequestParam("query") String query){
        List<User> userList = userService.searchUser(query);
        return userList;
    }

    @GetMapping("/api/user/profile")
    public User extractUserFromToken(@RequestHeader("Authorization") String jwt){
        User user = userService.findUserByJwt(jwt);
        return user;
    }
}