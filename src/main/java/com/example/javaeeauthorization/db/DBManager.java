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
}
