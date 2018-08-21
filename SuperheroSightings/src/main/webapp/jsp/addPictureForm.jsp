<%-- 
    Document   : addPictureForm
    Created on : Jul 31, 2018, 1:31:09 PM
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
        <title>Add A Profile Picture!</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body style = "background-color: #f0f0e8">
        <div class="container">
            <h1 style = "color: darkred">Add A Profile Picture!</h1>
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
                    <li role="presentation"
                        class="active">
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
            <h2>${errorMsg}</h2>
            <p><h4>Please upload an image for a superhero/supervillain profile:</h4></p>
        <form role="form" method="POST" 
              action="addProfilePhoto" 
              enctype="multipart/form-data">
            <div class="form-group">
                <label for="displayTitle">Display Title:</label>
                <input type="text" 
                       id="displayTitle" 
                       name="displayTitle"/>
            </div>
            <div class="form-group"
                 <label for="superhero" class="col-md-6 control-label">Select Superhero/Supervillain:</label>
                <div>
                    <select name="superhero" style="height: 35px; width: 293px">
                        <c:forEach items="${superheroList}" var="superhero">
                            <option value="${superhero.superheroID}">${superhero.superheroName}</option>
                        </c:forEach>
                    </select>
                </div>
                <h6><i>Note:  You must create the superhero first if the name is not on the list!</i></h6>
            </div>
            <div class="form-group">
                <label for="profilePhoto">Upload File:</label> 
                <input type="file" 
                       id="profilePhoto" 
                       name="profilePhoto"/>
            </div>
            <input type="submit" value="Upload Image"/>
        </form>
    </div>
    <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>

</body>
</html>

