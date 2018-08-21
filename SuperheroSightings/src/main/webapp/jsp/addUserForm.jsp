<%-- 
    Document   : addUserForm
    Created on : Aug 11, 2018, 2:45:39 PM
    Author     : brettwilkins
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Superhero Sightings</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body style = "background-color: #f0f0e8">
        <div class="container">
            <h1 style = "color: darkred">Superhero Sightings</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/">
                            Home
                        </a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/displaySuperheroesPage">
                            Superheroes/Supervillains
                        </a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/displaySightingsPage">
                            Sightings
                        </a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/displayLocationsPage">
                            Locations
                        </a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/displayOrganizationsPage">
                            Organizations
                        </a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/displaySuperpowersPage">
                            Superpowers
                        </a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/pictureGallery">
                            Picture Gallery
                        </a>
                    </li>
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/addPictureForm">
                            Add A Profile Picture!
                        </a>
                    </li>
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <li role="presentation">
                            <a href="${pageContext.request.contextPath}/displayUserList">
                                User Admin
                            </a>
                        </li>                        
                    </sec:authorize>
                </ul>    
            </div>
            <c:if test="${pageContext.request.userPrincipal.name != null}">
                <p>Hello : ${pageContext.request.userPrincipal.name}
                    | <a href="<c:url value="/j_spring_security_logout" />" > Logout</a>
                </p>
            </c:if>
            <div class="col-md-3">

            </div>
            <div class="col-md-6">
                <h3>Complete the following form to add a user:</h3>
                <form class = "form-horizontal" 
                      role = "form" method="POST" 
                      action="addUser">
                    <div class="form-group">
                        <label for="add-first-name" class="col-md-4 control-label">First Name:</label>
                        <div class="col-md-6">
                            <input type="text" class="form-control" maxlength="20" name="firstName" placeholder="First Name"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-last-name" class="col-md-4 control-label">Last Name:</label>
                        <div class="col-md-6">
                            <input type="text" class="form-control" maxlength="20" name="lastName" placeholder="Last Name"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-user-name" class="col-md-4 control-label">User Name:</label>
                        <div class="col-md-6">
                            <input type="text" class="form-control" maxlength="45" name="userName" placeholder="User Name"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-password" class="col-md-4 control-label">Password:</label>
                        <div class="col-md-6">
                            <input type="password" class="form-control" maxlength="45" name="password" placeholder="Password"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-admin-user" class="col-md-4 control-label">Admin User?:</label>
                        <div class="col-md-6">
                            <input type="checkbox" class="form-control" name="isAdmin" value="yes"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-md-offset-4 col-md-6">
                            <input type="submit" class="btn btn-default" value="Add User"/>
                        </div>
                    </div>
            </div>
            <div class="col-md-3">
            </div>
        </form>
    </div>
    <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>