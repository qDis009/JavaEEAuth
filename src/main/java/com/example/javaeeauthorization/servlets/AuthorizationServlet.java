package com.example.javaeeauthorization.servlets;

import com.example.javaeeauthorization.db.DBManager;
import com.example.javaeeauthorization.db.Users;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(value = "/auth")
public class AuthorizationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email=request.getParameter("email");
        String password=request.getParameter("password");
        String redirect="/login?emailerror";
        Users user= DBManager.getUser(email);
        if(user!=null){
            redirect="/login?passworderror";
            if(user.getPassword().equals(password)){
                request.getSession().setAttribute("currentUser",user);
                redirect="/";
            }
        }
        response.sendRedirect(redirect);
    }
}
