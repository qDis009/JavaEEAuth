<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.javaeeauthorization.db.Blog" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="vendor/head.jsp"%>
</head>
<body>
<%@include file="vendor/navbar.jsp"%>
<div class="container">
    <div class="row mt-3">
        <div class="col-sm-12">
            <%
                ArrayList<Blog> blogs=(ArrayList<Blog>) request.getAttribute("blogs");
                if(blogs!=null){
                    for(Blog blog:blogs){
            %>
            <div class="row mt-3">
                <div class="col-11 mx-auto p-3" style="background-color: lightgray;">
                    <h2><a href="/readblog?id=<%=blog.getId()%>" class="text-dark text-decoration-none"><%=blog.getTitle()%></a></h2>
                    <p class="mt-2"><%=blog.getContent()%></p>
                    <p class="mt-2">
                        Posted by <strong><%=blog.getUser().getFullName()%></strong>
                        at <strong><%=blog.getPostDate()%></strong>
                    </p>
                </div>
            </div>
            <%
                    }
                }
            %>
        </div>
    </div>
</div>
</body>
</html>
