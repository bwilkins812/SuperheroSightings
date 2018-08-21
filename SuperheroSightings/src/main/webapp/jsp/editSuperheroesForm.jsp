<%-- 
    Document   : editSuperheroesForm
    Created on : Jul 23, 2018, 5:16:16 PM
    Author     : brettwilkins
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!-- Directive for Spring Form tag libraries -->
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <title>Edit Superhero</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body style = "background-color: #f0f0e8">
        <div class="container">
            <h1 style = "color: darkred">Edit Superhero</h1>
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
            <sf:form class="form-horizontal" role="form" modelAttribute="superhero"
                     action="editSuperhero" method="POST">
                <div class="form-group">
                    <label for="add-sighting-name" class="col-md-4 control-label">Sighting:</label>
                    <div class="col-md-8">
                        <select name="sighting" style="height: 35px; width: 293px">
                            <c:forEach items="${sightingList}" var="sighting">
                                <option           
                                    value="${sighting.sightingID}">${sighting.sightingName}
                                </option>
                            </c:forEach>
                        </select>
                    </div>
                </div>

                <div class="form-group">
                    <label for="add-superhero-name" class="col-md-4 control-label">Superhero Name:</label>
                    <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-superhero-name"
                                  path="superheroName" placeholder="Superhero Name"/>
                        <sf:errors path="superheroName" cssclass="error"></sf:errors>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="add-superhero-description" class="col-md-4 control-label">Description:</label>
                        <div class="col-md-8">
                        <sf:input type="text" class="form-control" id="add-superhero-description"
                                  path="superheroDescription" placeholder="Superhero Description"/>
                        <sf:errors path="superheroDescription" cssclass="error"></sf:errors>
                        <sf:hidden path="superheroID"/>
                    </div>
                </div>

                <div class="form-group">
                    <label for="superpower" class="col-md-4 control-label">Select Superpower:</label>
                    <div class="col-md-8">
                        <select name="superpower" style="height: 35px; width: 293px">
                            <c:forEach items="${superpowerList}" var="superpower">
                                <option value="${superpower.superpowerID}">${superpower.powerName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <label for="organization" class="col-md-4 control-label">Select Organization:</label>
                    <div class="col-md-8">
                        <select name="organization" style="height: 35px; width: 293px">
                            <c:forEach items="${organizationList}" var="organization">
                                <option value="${organization.orgID}">${organization.orgName}</option>
                            </c:forEach>
                        </select>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-4 col-md-8">
                        <input type="submit" class="btn btn-default" value="Update Superhero"/>
                    </div>
                </div>
            </sf:form>
        </div>
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>