<%-- 
    Document   : superheroes
    Created on : Jul 21, 2018, 1:28:15 PM
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
        <title>Superheroes/Supervillains</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body style = "background-color: #f0f0e8">
        <div class="container">
            <h1 style = "color: darkred">Superheroes/Supervillains</h1>
            <hr/>
            <div class="navbar">
                <ul class="nav nav-tabs">
                    <li role="presentation">
                        <a href="${pageContext.request.contextPath}/">
                            Home
                        </a>
                    </li>          
                    <li role="presentation"
                        class="active">
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
            <div class="row">
                <div class="col-md-6"> 
                    <table id="contactTable" class="table table-hover">
                        <tr>
                            <th width="40%">Superheroes/Supervillains</th>
                            <th width="30%"></th>
                            <th width="15%"></th>
                            <th width="15%"></th>
                        </tr>
                        <c:forEach var="currentSuperhero" items="${superheroList}">
                            <tr>
                                <td>
                                    <a href="displaySuperheroDetails?superheroID=${currentSuperhero.superheroID}">
                                        <c:out value="${currentSuperhero.superheroName}"/>
                                    </a>
                                </td>
                                <td>
                                    <sec:authorize access="hasRole('ROLE_USER')">
                                        <a href="displayEditSuperheroForm?superheroID=${currentSuperhero.superheroID}">
                                            Edit
                                        </a> 
                                    </sec:authorize>
                                </td>
                                <td>
                                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                                        <a href="deleteSuperhero?superheroID=${currentSuperhero.superheroID}">
                                            Delete
                                            <a/>
                                        </sec:authorize>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            <sec:authorize access="hasRole('ROLE_USER')">
                <div class="col-md-6">
                    <h3 style = "text-align: center">Add New Superhero or Supervillain</h3>
                    <form class="form-horizontal" 
                          role="form" method="POST" 
                          action="createSuperhero">
                            <div class="form-group">
                                <label for="sighting" class="col-md-4 control-label">Select Sighting:</label>
                                <div class="col-md-8">
                                    <select name="sighting" style="height: 35px; width: 293px">
                                        <c:forEach items="${sightingList}" var="sighting">
                                            <option value="${sighting.sightingID}">${sighting.sightingName}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-superhero-name" class="col-md-4 control-label">Superhero Name:</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" maxlength="45" name="superheroName" placeholder="Superhero Name"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-superhero-description" class="col-md-4 control-label">Description:</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" maxlength="100" name="superheroDescription" placeholder="Superhero Description"/>
                                </div>
                            </div>      
                            <div class="form-group">
                                <label for="superpower" class="col-md-4 control-label">Superpowers:</label>
                                <div class="col-md-8">
                                    <select name="superpower" style="height: 35px; width: 293px">
                                        <c:forEach items="${superpowerList}" var="superpower">
                                            <option value="${superpower.superpowerID}">${superpower.powerName}</option>
                                        </c:forEach>
                                    </select> 
                                </div>                            
                            </div>           
                            <div class="form-group">
                                <label for="organization" class="col-md-4 control-label">Organizations:</label>
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
                                    <input type="submit" class="btn btn-default" value="Create Superhero"/>
                                </div>
                            </div>
                    </form>
                    <hr/>
                </div>
            </sec:authorize>
                <div class="col-md-6">
                </div> 
            <sec:authorize access="hasRole('ROLE_USER')">
                <div class="col-md-6">
                        <h3 style = "text-align: center">Add Additional Sightings</h3>
                        <h6 style = "text-align: center"><i>Note: You MUST create a superhero before you can add additional sightings!</i><h6>
                                <form class="form-horizontal" 
                                      role="form" method="POST" 
                                      action="addMultipleSightings">
                                    <div class="form-group">
                                        <label for="superhero" class="col-md-4 control-label">Superhero:</label>
                                        <div class="col-md-8">
                                            <select name="superhero" style="height: 35px; width: 293px">
                                                <c:forEach items="${superheroList}" var="superhero">
                                                    <option value="${superhero.superheroID}">${superhero.superheroName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>
                                    <div class="form-group">
                                        <label for="sighting" class="col-md-4 control-label">Sightings:</label>
                                        <div class="col-md-8">
                                            <select name="sighting" style="height: 35px; width: 293px">
                                                <c:forEach items="${sightingList}" var="sighting">
                                                    <option value="${sighting.sightingID}">${sighting.sightingName}</option>
                                                </c:forEach>
                                            </select>
                                        </div>
                                    </div>       
                                    <div class="form-group">
                                        <div class="col-md-offset-4 col-md-8">
                                            <input type="submit" class="btn btn-default" value="Add Sighting"/>
                                        </div>
                                    </div>
                                </form>
                    <hr/>
                </div>
            </sec:authorize>
                <div class="col-md-6">
                </div>
            <sec:authorize access="hasRole('ROLE_USER')">
                <div class="col-md-6">
                        <h3 style = "text-align: center">Add Additional Superpowers</h3>
                        <h6 style = "text-align: center"><i>Note: You MUST create a superhero before you can add additional superpowers!</i><h6>
                                <form class="form-horizontal" 
                                      role="form" method="POST" 
                                      action="addMultipleSuperpowers">
                                        <div class="form-group">
                                            <label for="superhero" class="col-md-4 control-label">Superhero:</label>
                                                <div class="col-md-8">
                                                    <select name="superhero" style="height: 35px; width: 293px">
                                                        <c:forEach items="${superheroList}" var="superhero">
                                                            <option value="${superhero.superheroID}">${superhero.superheroName}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="superpower" class="col-md-4 control-label">Superpowers:</label>
                                                <div class="col-md-8">
                                                    <select name="superpower" style="height: 35px; width: 293px">
                                                        <c:forEach items="${superpowerList}" var="superpower">
                                                            <option value="${superpower.superpowerID}">${superpower.powerName}</option>
                                                        </c:forEach>
                                                    </select> 
                                                </div>                            
                                        </div>              
                                        <div class="form-group">
                                                <div class="col-md-offset-4 col-md-8">
                                                    <input type="submit" class="btn btn-default" value="Add Superpower"/>
                                                </div>
                                        </div>
                                </form>
                    <hr/>
                </div> 
            </sec:authorize>
                <div class="col-md-6">
                </div>
            <sec:authorize access="hasRole('ROLE_USER')">
                <div class="col-md-6">
                        <h3 style = "text-align: center">Add Additional Organizations</h3>
                        <h6 style = "text-align: center"><i>Note: You MUST create a superhero before you can add additional organizations!</i><h6>
                                <form class="form-horizontal" 
                                      role="form" method="POST" 
                                      action="addMultipleOrgs">
                                        <div class="form-group">
                                            <label for="superhero" class="col-md-4 control-label">Superhero:</label>
                                                <div class="col-md-8">
                                                    <select name="superhero" style="height: 35px; width: 293px">
                                                        <c:forEach items="${superheroList}" var="superhero">
                                                            <option value="${superhero.superheroID}">${superhero.superheroName}</option>
                                                        </c:forEach>
                                                    </select>
                                                </div>
                                        </div>
                                        <div class="form-group">
                                            <label for="organization" class="col-md-4 control-label">Organizations:</label>
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
                                                <input type="submit" class="btn btn-default" value="Add Organization"/>
                                            </div>
                                        </div>
                                </form>
                    <hr/>
                </div> 
            </sec:authorize>
            </div>  
        </div>
    <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>