package com.example.javaeeauthorization.servlets;

import com.example.javaeeauthorization.db.Blog;
import com.example.javaeeauthorization.db.Comment;
import com.example.javaeeauthorization.db.DBManager;
import com.example.javaeeauthorization.db.Users;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(value = "/addcomment")
public class  AddCommentServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String redirect="/login";
        request.setCharacterEncoding("utf8");
        Users currentUser=(Users) request.getSession().getAttribute("currentUser");
        if(currentUser!=null){
            String commentText=request.getParameter("comment");
            Long blogId=Long.parseLong(request.getParameter("blog_id"));
            Blog blog= DBManager.getBlog(blogId);
            if(blog!=null){
                Comment comment=new Comment();
                comment.setComment(commentText);
                comment.setUser(currentUser);
                comment.setBlog(blog);
                if(DBManager.addComment(comment)){
                    redirect="/readblog?id="+blogId;
                }
            }
        }
        response.sendRedirect(redirect);
    }
}
