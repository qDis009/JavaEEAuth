package com.example.javaeeauthorization.servlets;

import com.example.javaeeauthorization.db.Blog;
import com.example.javaeeauthorization.db.Comment;
import com.example.javaeeauthorization.db.DBManager;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(value = "/readblog")
public class BlogDetailsServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long id=Long.parseLong(request.getParameter("id"));
        Blog blog= DBManager.getBlog(id);
        request.setAttribute("blog",blog);
        if(blog!=null){
            ArrayList<Comment> comments=DBManager.getAllComments(blog.getId());
            request.setAttribute("comments",comments);
        }
        request.getRequestDispatcher("/blogdetails.jsp").forward(request,response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
