<%@ page import="com.softserve.kolisnyk.model.User" %>
<%@ page import="java.util.List" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!doctype html>
<html lang="en">
<head>
    <title>Library</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="../static/css/bootstrap.min.css">
    <link rel="stylesheet" href="../static/css/fontawesome.min.css">
    <link rel="stylesheet" href="../static/css/bsadmin.css">
</head>
<body>
<%
    String name =
            request.getSession().getAttribute("loggedUser") != null ? ((User) request.getSession()
                    .getAttribute("loggedUser")).getName() : "";
    List<User> userList = (List<User>) request.getAttribute("list");
    System.out.println(userList);
%>
<nav class="navbar navbar-expand navbar-dark bg-primary">
    <a class="sidebar-toggle text-light mr-3"><i class="fa fa-bars"></i></a>
    <a class="navbar-brand" href="#"><i class="fa fa-code-branch"></i> Library</a>
    <div class="navbar-collapse collapse">
        <ul class="navbar-nav ml-auto">

            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="" id="navbarDropdownMenuLink"
                   data-toggle="dropdown">
                    <i class="fa fa-user"></i> <%=name%>
                </a>
                <div class="dropdown-menu dropdown-menu-right"
                     aria-labelledby="navbarDropdownMenuLink">
                    <a class="dropdown-item" href="/logout">Logout</a>
                </div>
            </li>
        </ul>
    </div>
</nav>
<div class="d-flex">
    <nav class="sidebar bg-dark">
        <ul class="list-unstyled">
            <li><a href="user/book-shop"><i class="fa fa-book fa-fw"></i> Books</a></li>
            <li><a href="mybooks"><i class="fa fa-bookmark fa-fw"></i> My books</a></li>
            <li><a href="myfines"><i class="fa fa-bolt fa-fw"></i> My fines</a></li>

        </ul>
        <hr class="my-4">
        <ul class="list-unstyled">
            <li><a href="#"><i class="fa fa-asterisk fa-fw"></i> About</a></li>

        </ul>
    </nav>
    <div class="content p-4">

        <table class="table">
            <thead>
            <tr>
                <th scope="col">#</th>
                <th scope="col">Author</th>
                <th scope="col">Name</th>
                <th scope="col">Year</th>
                <th scope="col"></th>
            </tr>
            </thead>
            <tbody>
            <%
                int i = 0;
                for (User post : userList) {
            %>
            <tr>
                <td><%=i++%>
                </td>
                <td><%=post.getName()%>
                </td>
                <td><%=post.getSurName()%>
                </td>
                <td><%=post.getEmail()%>
                </td>
                <td align='center'>
                    <form>
                        <button type="button" class="btn btn-success">BORROW</button>
                    </form>
                </td>
            </tr>
            <%}%>
            <%--<c:forEach var="item" items="<%=userList%>">--%>
            <%--<tr>--%>
            <%--<th scope="row">3</th>--%>
            <%--<td><%=item%></td>--%>

            <%--<td><c:out value="<%=item.id%>" /></td>--%>
            <%--<td><c:out value="${item.surname}" /></td>--%>
            <%--<td>Larry</td>--%>
            <%--<td>the Bird</td>--%>
            <%--<td align='center'><form><button type="button" class="btn btn-success">Success</button></form></td>--%>
            <%--</tr>--%>
            <%--</c:forEach>--%>
            </tbody>
        </table>
    </div>
</div>
<script src="../static/js/jquery.min.js"></script>
<script src="../static/js/popper.min.js"></script>
<script src="../static/js/bootstrap.min.js"></script>
<script src="../static/js/bsadmin.js"></script>
</body>
</html>