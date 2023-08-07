package com.example.javaeeauthorization.servlets;

import com.example.javaeeauthorization.db.Blog;
import com.example.javaeeauthorization.db.DBManager;
import com.example.javaeeauthorization.db.Users;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(value = "/addblog")
public class AddBlogServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Users currentUser=(Users) request.getSession().getAttribute("currentUser");
        if(currentUser!=null){
            request.getRequestDispatcher("/addblog.jsp").forward(request,response);
        }else{
            response.sendRedirect("/login");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String redirect="/login";
        request.setCharacterEncoding("utf8");
        Users currentUser=(Users) request.getSession().getAttribute("currentUser");
        if(currentUser!=null){
            redirect="/addblog?error";
            String title=request.getParameter("title");
            String content=request.getParameter("content");
            Blog blog=new Blog();
            blog.setTitle(title);
            blog.setContent(content);
            blog.setUser(currentUser);
            if(DBManager.addBlog(blog)){
                redirect="/addblog?success";
            }
        }
        response.sendRedirect(redirect);
    }
}
