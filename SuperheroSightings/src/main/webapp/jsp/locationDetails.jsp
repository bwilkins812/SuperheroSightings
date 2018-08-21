<%-- 
    Document   : locationDetails
    Created on : Jul 24, 2018, 12:45:13 PM
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
        <title>Location Details</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body style = "background-color: #f0f0e8">
        <div class="container">
            <h1 style = "color: darkred">Location Details</h1>
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
            <div class="table-responsive col-md-6">          
                <table class="table">
                    <thead>
                        <tr>
                            <th><h4>Location</h4></th>
                            <th><h4>Location Details</h4></th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>Location:</td>
                            <td><c:out value="${location.locationName}"/></td>
                        </tr>
                        <tr>
                            <td>Description:</td>
                            <td><c:out value="${location.locationDescription}"/></td>
                        </tr>
                        <tr>
                            <td>Address:</td>
                            <td><c:out value="${location.address}"/></td>
                        </tr>
                        <tr>
                            <td>Latitude:</td>
                            <td><c:out value="${location.latitude}"/></td>
                        </tr>
                        <tr>
                            <td>Longitude:</td>
                            <td><c:out value="${location.longitude}"/></td>
                        </tr>
                        <tr>
                            <td>Sightings at this location:</td>
                            <td><c:forEach items="${sightingList}" var="sightingList" varStatus = "loop">${sightingList.sightingName}<c:if test="${!loop.last}">,</c:if>    
                                </c:forEach></td>
                        </tr>
                    </tbody>
                </table>
            </div>
            <div class="col-md-3">
            </div>
        </div>
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>