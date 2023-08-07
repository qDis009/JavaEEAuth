<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%@include file="vendor/head.jsp"%>
</head>
<body>
<%@include file="vendor/navbar.jsp"%>
<div class="container">
    <div class="row mt-5">
        <div class="col-sm-6 offset-3">
            <h4 class="mb-4">
                LOGIN TO SYSTEM
            </h4>
            <%
                String passError=request.getParameter("passworderror");
                if(passError!=null){
            %>
            <div class="alert alert-danger" role="alert">
                Incorrect password!
            </div>
            <%
                }
            %>
            <%
                String emailError=request.getParameter("emailerror");
                if(emailError!=null){
            %>
            <div class="alert alert-danger" role="alert">
                Incorrect email!
            </div>
            <%
                }
            %>
            <form action="/auth" method="post">
                <div class="form-group">
                    <label>Email: <input type="email" name="email" class="form-control"></label>
                </div>
                <div class="form-group mb-2">
                    <label>Password: <input type="password" name="password" class="form-control"></label>
                </div>
                <div class="form-group">
                    <button class="btn btn-success">Sign in</button>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
