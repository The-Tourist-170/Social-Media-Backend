package com.tourist.toddle.Models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

@Entity
public class Cmt {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    private String cmt;
    
    @ManyToOne
    private User user;

    @ManyToMany
    private List<User> likes = new ArrayList<>();

    private LocalDateTime cmtAt;

    public Cmt(Integer id, String cmt, User user, List<User> likes, LocalDateTime cmtAt) {
        this.id = id;
        this.cmt = cmt;
        this.user = user;
        this.likes = likes;
        this.cmtAt = cmtAt;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getCmt() {
        return cmt;
    }

    public void setCmt(String cmt) {
        this.cmt = cmt;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<User> getLikes() {
        return likes;
    }

    public void setLikes(List<User> liked) {
        this.likes = liked;
    }

    public LocalDateTime getCmtAt() {
        return cmtAt;
    }

    public void setCmtAt(LocalDateTime cmtAt) {
        this.cmtAt = cmtAt;
    }

    public Cmt() {
    }
}
