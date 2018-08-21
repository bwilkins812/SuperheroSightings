<%-- 
    Document   : sightings
    Created on : Jul 21, 2018, 1:29:13 PM
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
        <title>Sightings</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body style = "background-color: #f0f0e8">
        <div class="container">
            <h1 style = "color: darkred">Sightings</h1>
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
                    <li role="presentation"
                        class="active">
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
            <div class="row">  
                <div class="col-md-6">
                    <table id="contactTable" class="table table-hover">
                        <tr>
                            <th width="40%">Sighting</th>
                            <th width="30%"></th>
                            <th width="15%"></th>
                            <th width="15%"></th>
                        </tr>
                        <c:forEach var="currentSighting" items="${sightingList}">
                            <tr>
                                <td>
                                    <a href="displaySightingDetails?sightingID=${currentSighting.sightingID}">
                                        <c:out value="${currentSighting.sightingName}"/>
                                    </a>
                                </td>
                                <td>
                                    <a href="displayEditSightingForm?sightingID=${currentSighting.sightingID}">
                                        Edit
                                    </a>
                                </td>
                                <td>
                                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                                        <a href="deleteSighting?sightingID=${currentSighting.sightingID}">
                                            Delete
                                            <a/>
                                        </sec:authorize>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div> 
                <div class="col-md-6">
                    <h2>Add New Sighting</h2>
                    <form class="form-horizontal" 
                          role="form" method="POST" 
                          action="createSighting">
                        <div class="form-group">
                            <label for="add-sighting-name" class="col-md-4 control-label">Sighting Name:</label>
                            <div class="col-md-8">
                                <input type="text" class="form-control" maxlength="45" name="sightingName" placeholder="Sighting Name"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="add-sighting-date" class="col-md-4 control-label">Sighting Date:</label>
                            <div class="col-md-8">
                                <input type="date" class="form-control" name="sightingDate" placeholder="Sighting Date"/>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-md-offset-4 col-md-8">
                                <input type="submit" class="btn btn-default" value="Create Sighting"/>
                            </div>
                        </div>
                    </form>
                    <hr/>
                    <h2>Add A Picture!</h2>
                    <h2>${errorMsg}</h2>
                    <p><h4>Please upload an image for the superhero/supervillain sighting:</h4></p>
                    <form role="form" method="POST" 
                          action="addSightingPhoto" 
                          enctype="multipart/form-data">
                        <div class="form-group">
                            <label for="displayTitle">Display Title:</label>
                            <input type="text" 
                                   id="displayTitle" 
                                   name="displayTitle"/>
                        </div>
                        <div class="form-group"
                             <label for="sighting" class="col-md-6 control-label">Select Sighting:</label>
                            <div>
                                <select name="sighting" style="height: 35px; width: 293px">
                                    <c:forEach items="${sightingList}" var="sighting">
                                        <option value="${sighting.sightingID}">${sighting.sightingName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="sightingPhoto">Upload File:</label> 
                            <input type="file" 
                                   id="sightingPhoto" 
                                   name="sightingPhoto"/>
                        </div>
                        <input type="submit" value="Upload Picture"/>
                    </form>
                </div> 
            </div>            
        </div>
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>


