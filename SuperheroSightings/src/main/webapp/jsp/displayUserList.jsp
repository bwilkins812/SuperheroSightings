<%-- 
    Document   : displayUserList
    Created on : Aug 11, 2018, 2:44:48 PM
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
            <a href="displayUserForm">Add a User</a><br/>
            <hr/>
            <div class="col-md-3">           
            </div>        
            <div class="col-md-6">
                <h3>Superhero Sightings Users</h3>
                <table id="userTable" class="table table-hover">
                    <tr>
                        <th width="30%">First Name</th>
                        <th width="30%">Last Name</th>
                        <th width="30%">User Name</th>
                        <th width="10%"></th>
                    </tr>
                    <c:forEach var="currentUser" items="${users}">
                        <tr>
                            <td>
                                <c:out value="${currentUser.firstName}"/> 
                            </td>
                            <td>
                                <c:out value="${currentUser.lastName}"/> 
                            </td>
                            <td>
                                <c:out value="${currentUser.userName}"/> 
                            </td>
                            <td>

                            </td>
                            <td>
                                <sec:authorize access="hasRole('ROLE_ADMIN')">
                                    <a href="displayEditUsersForm?userID=${currentUser.userID}">
                                        Edit
                                    </a>
                                </sec:authorize>
                            </td>
                            <td>
                                <sec:authorize access="hasRole('ROLE_ADMIN')">
                                    <a href="deleteUser?userID=${currentUser.userID}">
                                        Disable
                                    </a>
                                </sec:authorize>
                            </td>
                        </tr>
                    </c:forEach>
                </table>                    
            </div>
            <div class="col-md-3">        
            </div>  
        </div>
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>





