<%-- 
    Document   : superpowers
    Created on : Jul 21, 2018, 1:29:24 PM
    Author     : brettwilkins
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Superpowers</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body style = "background-color: #f0f0e8">
        <div class="container">
            <h1 style = "color: darkred">Superpowers</h1>
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
                    <li role="presentation"
                        class="active">
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
            <div class="row">               
                <div class="col-md-6">
                    <table id="contactTable" class="table table-hover">
                        <tr>
                            <th width="40%">Superpower</th>
                            <th width="30%"></th>
                            <th width="15%"></th>
                            <th width="15%"></th>
                        </tr>
                        <c:forEach var="currentSuperpower" items="${superpowerList}">
                            <tr>
                                <td>
                                    <a href="displaySuperpowerDetails?superpowerID=${currentSuperpower.superpowerID}">
                                        <c:out value="${currentSuperpower.powerName}"/>
                                    </a>
                                </td>
                                <td>
                                    <a href="displayEditSuperpowerForm?superpowerID=${currentSuperpower.superpowerID}">
                                        Edit
                                    </a>
                                </td>
                                <td>
                                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                                        <a href="deleteSuperpower?superpowerID=${currentSuperpower.superpowerID}">
                                            Delete
                                            <a/>
                                        </sec:authorize>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div> 
                <div class="col-md-6">
                    <h2>Add New Superpower</h2>
                    <form class="form-horizontal" 
                          role="form" method="POST" 
                          action="createSuperpower">
                        <div class="form-group">
                            <label for="add-power-name" class="col-md-4 control-label">Superpower Name:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" maxlength="45" name="powerName" placeholder="Superpower Name"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-power-description" class="col-md-4 control-label">Description:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" maxlength="100" name="powerDescription" placeholder="Superpower Description"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" class="btn btn-default" value="Create Superpower"/>
                            </div>
                        </div>
                    </form>
                </div>
            </div>  
        </div>
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>