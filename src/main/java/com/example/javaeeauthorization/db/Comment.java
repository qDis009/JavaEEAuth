package com.example.javaeeauthorization.db;

import java.sql.Timestamp;

public class Comment {
    private Long id;
    private Users user;
    private Blog blog;
    private String comment;
    private Timestamp postDate;
    public Comment(){}

    public Comment(Long id, Users user, Blog blog, String comment, Timestamp postDate) {
        this.id = id;
        this.user = user;
        this.blog = blog;
        this.comment = comment;
        this.postDate = postDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Blog getBlog() {
        return blog;
    }

    public void setBlog(Blog blog) {
        this.blog = blog;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Timestamp getPostDate() {
        return postDate;
    }

    public void setPostDate(Timestamp postDate) {
        this.postDate = postDate;
    }
}
