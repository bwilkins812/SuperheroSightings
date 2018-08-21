<%-- 
    Document   : organizations
    Created on : Jul 21, 2018, 1:28:53 PM
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
        <title>Organizations</title>
        <link href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet">        
    </head>
    <body style = "background-color: #f0f0e8">
        <div class="container">
            <h1 style = "color: darkred">Organizations</h1>
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
                    <li role="presentation"
                        class="active">
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
                    <table id="orgTable" class="table table-hover">
                        <tr>
                            <th width="40%">Organization</th>
                            <th width="30%"></th>
                            <th width="15%"></th>
                            <th width="15%"></th>
                        </tr>
                        <c:forEach var="currentOrganization" items="${orgList}">
                            <tr>
                                <td>
                                    <a href="displayOrganizationDetails?orgID=${currentOrganization.orgID}">
                                        <c:out value="${currentOrganization.orgName}"/>
                                    </a>
                                </td>
                                <td>
                                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                                        <a href="displayEditOrganizationForm?orgID=${currentOrganization.orgID}">
                                            Edit
                                        </a>
                                    </sec:authorize>
                                </td>
                                <td>
                                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                                        <a href="deleteOrganization?orgID=${currentOrganization.orgID}">
                                            Delete
                                            <a/>
                                        </sec:authorize>
                                </td>
                            </tr>
                        </c:forEach>
                    </table>                    
                </div>             
                <div class="col-md-6">
                    <sec:authorize access="hasRole('ROLE_ADMIN')">
                        <h2>Add New Organization</h2>
                        <form class="form-horizontal" 
                              role="form" method="POST" 
                              action="createOrganization">
                            <div class="form-group">
                                <label for="add-org-name" class="col-md-4 control-label">Organization Name:</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" maxlength="45" name="orgName" placeholder="Organization Name"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-org-description" class="col-md-4 control-label">Organization Description:</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" maxlength="100" name="orgDescription" placeholder="Organization Description"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="add-org-address" class="col-md-4 control-label">Address:</label>
                                <div class="col-md-8">
                                    <input type="text" class="form-control" maxlength="45" name="orgAddress" placeholder="Organization Address"/>
                                </div>
                            </div>
                            <div class="form-group">
                                <div class="col-md-offset-4 col-md-8">
                                    <input type="submit" class="btn btn-default" value="Create Organization"/>
                                </div>
                            </div>
                        </form>
                    </sec:authorize>
                </div> 
            </div>           
        </div>        
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>