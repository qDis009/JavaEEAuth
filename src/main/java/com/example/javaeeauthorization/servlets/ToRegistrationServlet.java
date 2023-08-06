package com.example.javaeeauthorization.servlets;

import com.example.javaeeauthorization.db.DBManager;
import com.example.javaeeauthorization.db.Users;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet(value = "/toregistration")
public class ToRegistrationServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String email=request.getParameter("email");
        String password=request.getParameter("password");
        String rePassword=request.getParameter("re_password");
        String fullName=request.getParameter("fullName");
        String redirect="/registration?passworderror";
        if(password.equals(rePassword)){
            redirect="/registration?emailerror";
            Users user= DBManager.getUser(email);
            if(user==null){
                Users newUser=new Users(null,email,password,fullName);
                if(DBManager.addUser(newUser)) {
                    redirect = "/registration?success";
                }
            }
        }
        response.sendRedirect(redirect);
    }
}
