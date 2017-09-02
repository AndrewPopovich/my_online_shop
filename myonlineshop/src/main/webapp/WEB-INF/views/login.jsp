<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:url var="css" value="/resources/css"/>
<spring:url var="js" value="/resources/js"/>
<spring:url var="images" value="/resources/images"/>

<c:set var="contextRoot" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<head>

    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="description" content="">
    <meta name="author" content="">

    <title>My shop - ${title}</title>

    <script>
        window.menu = '${title}';
        window.contextRoot = '${contextRoot}'
    </script>

    <!-- Bootstrap Core CSS -->
    <link href="${css}/bootstrap.min.css" rel="stylesheet">

    <!-- Bootstrap Solar theme CSS -->
    <link href="${css}/bootstrap-superhero-theme.css" rel="stylesheet">

    <!-- Bootstrap DataTables -->
    <link href="${css}/dataTables.bootstrap.css" rel="stylesheet">

    <!-- Custom CSS -->
    <link href="${css}/myapp.css" rel="stylesheet">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/libs/html5shiv/3.7.0/html5shiv.js"></script>
    <script src="https://oss.maxcdn.com/libs/respond.js/1.4.2/respond.min.js"></script>
    <![endif]-->

</head>

<body>

<div class="wrapper">

    <!-- Navigation -->
    <nav class = "navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <div class="navbar-header">
                <a class="navbar-brand" href="${contextRoot}/home">Online Shop</a>
            </div>
        </div>
    </nav>
    <!-- Page Content -->

    <div class="content">
        <div class="container">

        <c:if test="${not empty message}">
            <div class="row">
                <div class="col-md-offset-3 col-md-6">
                    <div class="alert alert-danger">
                        ${message}
                    </div>
                </div>
            </div>
        </c:if>

        <c:if test="${not empty logout}">
            <div class="row">
                <div class="col-md-offset-3 col-md-6">
                    <div class="alert alert-success">
                        ${logout}
                    </div>
                </div>
            </div>
            </c:if>

            <div class="col-md-offset-3 col-md-6">
                <div class="row">
                    <div class="col-md-12">
                        <div class="panel panel-default">
                            <div class="panel-heading">
                                <span class="glyphicon glyphicon-lock"></span> Login</div>
                            <div class="panel-body">
                                <form class="form-horizontal" role="form" action="${contextRoot}/login"
                                method="POST" class="form-horizontal" id="loginForm">
                                    <div class="form-group">
                                        <label for="inputEmail3" class="col-sm-3 control-label">
                                            Email</label>
                                        <div class="col-sm-9">
                                            <input type="text" name="username" id="username" class="form-control" placeholder="Email" required/>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="inputPassword3" class="col-sm-3 control-label">
                                            Password</label>
                                        <div class="col-sm-9">
                                            <input type="password" name="password" id="password" class="form-control" placeholder="Password" required/>
                                        </div>
                                    </div>

                                    <div class="form-group last">
                                        <div class="col-sm-offset-3 col-sm-9">
                                           <input type="submit" value="Login" class="btn btn-primary"/>
                                           <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
                                        </div>
                                    </div>
                                </form>
                            </div>
                            <div class="panel-footer">
                                Not Registered? <a href="${contextRoot}/register">Register here</a>
                            </div>
                        </div>
                    </div>
                </div>
                </div>
            </div>
        </div>
    </div>

    <!-- Footer -->
    <%@include file="./shared/footer.jsp" %>

    <!-- jQuery -->
    <script src="${js}/jquery.js"></script>

    <!-- jQuery validate-->
    <script src="${js}/jquery.validate.js"></script>

    <!-- Bootstrap Core JavaScript -->
    <script src="${js}/bootstrap.min.js"></script>

    <!-- Self coded JS -->
    <script src="${js}/myapp.js"></script>

</div>

</body>

</html>
