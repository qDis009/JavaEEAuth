<%@ page import="java.util.ArrayList" %>
<%@ page import="com.example.javaeeauthorization.db.Blog" %>
<%@ page import="com.example.javaeeauthorization.db.Comment" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="vendor/head.jsp"%>
</head>
<body>
<%@include file="vendor/navbar.jsp"%>
<div class="container" style="min-height: 500px">
    <div class="row mt-3">
        <div class="col-sm-12">
            <%
                Blog blog=(Blog) request.getAttribute("blog");
                if(blog!=null){
            %>
            <div class="row mt-3">
                <div class="col-11 mx-auto p-3" style="background-color: lightgray">
                    <h2 class="mt-2"><%=blog.getTitle()%></h2>
                    <p class="mt-2"><%=blog.getContent()%></p>
                    <p class="mt-2">
                        Posted by <strong><%=blog.getUser().getFullName()%></strong>
                        at <strong><%=blog.getPostDate()%></strong>
                    </p>
                    <%
                        if(online){
                    %>
                    <div class="row mt-3">
                        <div class="col-12">
                            <form action="/addcomment" method="post">
                                <input type="hidden" name="blog_id" value="<%=blog.getId()%>">
                                <textarea class="form-control" name="comment" placeholder="Write a comment"></textarea>
                                <button class="btn btn-success mt-3">Add comment</button>
                            </form>
                        </div>
                    </div>
                    <%
                        }
                    %>
                    <hr>
                    <%
                        ArrayList<Comment> comments=(ArrayList<Comment>) request.getAttribute("comments");
                        if(comments!=null){
                            for(Comment comment:comments){
                    %>
                    <div class="row mt-2">
                        <div class="col-12">
                            <h5><%=comment.getUser().getFullName()%></h5>
                            <p><%=comment.getComment()%></p>
                            <p>At<strong><%=comment.getPostDate()%></strong></p>
                        </div>
                    </div>
                    <%
                            }
                        }
                    %>
                </div>
            </div>
            <%
                }
            %>
        </div>
    </div>
</div>
</body>
</html>
