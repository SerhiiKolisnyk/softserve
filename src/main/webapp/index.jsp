<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Library</title>
    <!-- Bootstrap style-->
    <link href="static/css/bootstrap.min.css" rel="stylesheet">
    <link href="static/css/sign.css" rel="stylesheet">


</head>

<body>
<%
    String email =
            request.getAttribute("nEmail") != null ? (String) request.getAttribute("nEmail") : "";
    String password =
            request.getAttribute("nPassword") != null ? (String) request.getAttribute("nPassword")
                    : "";
%>
<div class="container">
    <div class="row">
        <div class="col-sm-9 col-md-7 col-lg-5 mx-auto">
            <div class="card card-signin my-5">
                <div class="card-body" >
                    <h5 class="card-title text-center">Sign In</h5>
                    <form class="form-signin"  method="post" action="login">
                        <div class="form-label-group">
                            <input type="email" id="inputEmail" class="form-control"
                                   placeholder="Email address" name="nEmail" required autofocus
                                   value=<%=email%>>
                            <label for="inputEmail">Email address</label>
                        </div>

                        <div class="form-label-group">
                            <input type="password" id="inputPassword" class="form-control"
                                   placeholder="Password" name="nPassword" required
                                   value=<%=password%>>
                            <label for="inputPassword">Password</label>
                        </div>

                        <div class="custom-control custom-checkbox mb-3">
                            <input type="checkbox" class="custom-control-input" id="customCheck1" name="nRememberMe">
                            <label class="custom-control-label" for="customCheck1">Remember
                                password</label>
                        </div>
                        <p class="text-danger"><%
                            if (request.getAttribute("message") != null) {
                                out.println(request.getAttribute("message"));
                            }
                        %></p>
                        <button class="btn btn-lg btn-primary btn-block text-uppercase"
                                type="submit">Sign in
                        </button>
                        <hr class="my-4">
                        <a href="register.jsp" class="btn btn-lg btn-google btn-block text-uppercase"
                           role="button"><i class="fab fa-google mr-2"></i> Sign up
                        </a>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

<script src="static/js/jquery.js"></script>
<script src="static/js/bootstrap.min.js"></script>
</html>