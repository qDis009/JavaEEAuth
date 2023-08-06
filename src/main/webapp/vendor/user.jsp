<%@ page import="com.example.javaeeauthorization.db.Users" %><%
    Users currentUser=(Users) request.getSession().getAttribute("currentUser");
    boolean online=false;
    if(request.getSession().getAttribute("currentUser")!=null){
        online=true;
    }
%>