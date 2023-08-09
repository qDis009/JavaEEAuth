package com.example.javaeeauthorization.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class DBManager {
    private static Connection connection;

    static {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/auth?useUnicode=true&serverTimezone=UTC", "root", "General12345!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Users getUser(String email) {
        Users user = null;
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM users WHERE email = ? LIMIT 1");
            statement.setString(1, email);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                user = new Users();
                user.setEmail(resultSet.getString("email"));
                user.setId(resultSet.getInt("id"));
                user.setPassword(resultSet.getString("password"));
                user.setFullName(resultSet.getString("full_name"));
            }
            statement.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
    public static boolean addUser(Users user){
        int rows=0;
        try {
            PreparedStatement statement=connection.prepareStatement("insert into users (email,password,full_name) "+
                    " values (?,?,?)");
            statement.setString(1,user.getEmail());
            statement.setString(2,user.getPassword());
            statement.setString(3,user.getFullName());
            rows=statement.executeUpdate();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return rows>0;
    }
    public static boolean addBlog(Blog blog){
        int rows=0;
        try {
            PreparedStatement statement=connection.prepareStatement(
                    "insert into blogs (user_id,title,content,post_date) values (?,?,?,NOW())");
            statement.setLong(1,blog.getUser().getId());
            statement.setString(2,blog.getTitle());
            statement.setString(3,blog.getContent());
            rows=statement.executeUpdate();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return rows>0;
    }
    public static ArrayList<Blog> getAllBlogs(){
        ArrayList<Blog> blogs=new ArrayList<>();
        try {
            PreparedStatement statement=connection.prepareStatement(""+
                    "select b.id,b.title,b.content,b.post_date,b.user_id,u.full_name,u.email "+
                    "from blogs b "+
                    "inner join users u on u.id=b.user_id "+
                    "order by b.post_date desc ");
            ResultSet resultSet=statement.executeQuery();
            while (resultSet.next()){
                Blog blog=new Blog();
                blog.setId(resultSet.getLong("id"));
                blog.setTitle(resultSet.getString("title"));
                blog.setContent(resultSet.getString("content"));
                blog.setPostDate(resultSet.getTimestamp("post_date"));
                Users user=new Users();
                user.setId(resultSet.getInt("id"));
                user.setEmail(resultSet.getString("email"));
                user.setFullName(resultSet.getString("full_name"));
                blog.setUser(user);
                blogs.add(blog);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return blogs;
    }
    public static Blog getBlog(Long id){
        Blog blog=null;
        try{
            PreparedStatement statement=connection.prepareStatement(""+
                    "select b.id,b.title,b.content,b.post_date,b.user_id,u.full_name,u.email "+
                    "from blogs b " +
                    "inner join users u on u.id=b.user_id " +
                    "where b.id=?");
            statement.setLong(1,id);
            ResultSet resultSet=statement.executeQuery();
            while (resultSet.next()){
                blog=new Blog();
                blog.setId(resultSet.getLong("id"));
                blog.setTitle(resultSet.getString("title"));
                blog.setContent(resultSet.getString("content"));
                blog.setPostDate(resultSet.getTimestamp("post_date"));
                Users user=new Users();
                user.setId(resultSet.getInt("user_id"));
                user.setEmail(resultSet.getString("email"));
                user.setFullName(resultSet.getString("full_name"));
                blog.setUser(user);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return blog;
    }
    public static boolean addComment(Comment comment){
        int rows=0;
        try {
            PreparedStatement statement=connection.prepareStatement(""+
                    "insert into comments(user_id,blog_id,comment,post_date) "+
                    "values (?,?,?,NOW())");
            statement.setLong(1,comment.getUser().getId());
            statement.setLong(2,comment.getBlog().getId());
            statement.setString(3,comment.getComment());
            rows=statement.executeUpdate();
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return rows>0;
    }
    public static ArrayList<Comment> getAllComments(Long blogId){
        ArrayList<Comment> comments=new ArrayList<>();
        try {
            PreparedStatement statement=connection.prepareStatement(""+
                    "select c.id,c.user_id,c.blog_id,c.comment,u.full_name,u.email,c.post_date "+
                    "from comments c "+
                    "inner join users u on c.user_id=u.id "+
                    "where c.blog_id=? "+
                    "order by c.post_date desc");
            statement.setLong(1,blogId);
            ResultSet resultSet=statement.executeQuery();
            while (resultSet.next()){
                Comment comment=new Comment();
                comment.setId(resultSet.getLong("id"));
                comment.setComment(resultSet.getString("comment"));
                comment.setPostDate(resultSet.getTimestamp("post_date"));
                Users user=new Users();
                user.setId(resultSet.getInt("user_id"));
                user.setFullName(resultSet.getString("full_name"));
                user.setEmail(resultSet.getString("email"));
                comment.setUser(user);
                Blog blog=new Blog();
                blog.setId(resultSet.getLong("blog_id"));
                comment.setBlog(blog);
                comments.add(comment);
            }
            statement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
        return comments;
    }
}
