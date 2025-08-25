package com.tourist.toddle.Models;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "\"user\"")
public class User {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private String fName;
    private String lName;
    private String email;
    private String pass;
    private List<Integer> followers = new ArrayList<>();
    private List<Integer> followings = new ArrayList<>();
    private String gender;

    @JsonIgnore
    @ManyToMany
    private List<Post> savedPosts = new ArrayList<>();

    public void setId(Integer id) {
        this.id = id;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }

    public void setlName(String lName) {
        this.lName = lName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public Integer getId() {
        return id;
    }

    public String getfName() {
        return fName;
    }

    public String getlName() {
        return lName;
    }

    public User(Integer id, String fName, String lName, String email, String pass, List<Integer> followers,
            List<Integer> followings, String gender, List<Post> savedPosts) {
        this.id = id;
        this.fName = fName;
        this.lName = lName;
        this.email = email;
        this.pass = pass;
        this.followers = followers;
        this.followings = followings;
        this.gender = gender;
        this.savedPosts = savedPosts;
    }

    public String getEmail() {
        return email;
    }

    public void setFollowers(List<Integer> followers) {
        this.followers = followers;
    }

    public void setFollowings(List<Integer> followings) {
        this.followings = followings;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPass() {
        return pass;
    }

    public User(){

    }

    public List<Integer> getFollowers() {
        return followers;
    }

    public List<Integer> getFollowings() {
        return followings;
    }

    public String getGender() {
        return gender;
    }

    public List<Post> getSavedPosts() {
        return savedPosts;
    }

    public void setSavedPosts(List<Post> savedPosts) {
        this.savedPosts = savedPosts;
    }
    
}
