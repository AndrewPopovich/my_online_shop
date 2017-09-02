<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<spring:url var="css" value="/resources/css"/>

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

    <!-- Custom CSS -->
    <link href="${css}/myapp.css" rel="stylesheet">

</head>

<body>

<div class="wrapper">

    <!-- Navigation -->
    <nav class="navbar navbar-inverse navbar-fixed-top" role="navigation">
        <div class="container">
            <!-- Brand and toggle get grouped for better mobile display -->
            <div class="navbar-header">
                <a class="navbar-brand" href="${contextRoot}/home">Home</a>
            </div>
        </div>
    </nav>

    <!-- Page Content -->

    <div class="content">

        <div class="container">
            <div class="row">
                <div class="col-xs-12">
                    <div class="jumbotron">
                    <center>
                        <h1>
                        Oops!</h1>
                        <h2>${errorTitle}</h2>
                        <hr/>
                        <blockquote style="word-wrap:break-word">
                            ${errorDescription}
                        </blockquote>
                        <div class="error-actions">
                            <a href="${contextRoot}/home" class="btn btn-primary btn-lg"><span class="glyphicon glyphicon-home"></span>
                                Take Me Home </a><a href="${contextRoot}/contact" class="btn btn-default btn-lg"><span class="glyphicon glyphicon-envelope"></span> Contact Support </a>
                        </div>
                        </center>
                    </div>
                </div>
            </div>
        </div>

    </div>

    <!-- Footer -->
    <%@include file="./shared/footer.jsp" %>
</div>
</body>
</html>

