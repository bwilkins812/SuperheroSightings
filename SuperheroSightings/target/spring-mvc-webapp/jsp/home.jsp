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
            <div class="col-md-6">                
                <p>
                <h4>Welcome to Superhero Sightings!</h4>  
                </p>
                <p>
                    <b>Please follow these instructions to maximize your Superhero Sightings experience!</b>
                </p>
                <p>
                    1.  Click on the Superheroes/Supervillains tab and you will see drop down menus and fields where you can select and enter information
                    about a superhero or a supervillain.  It is important to note here that there is a default selection of "none" on the drop down menus, 
                    so you can just add a superhero name and description if you want, but you can also select a sighting, superpower, or organization
                    to add further details to the superhero's profile.  NOTE:  If you do not see the sighting, organization, or superpower you MUST add
                    it first under the appropriate tab and then it will appear in the drop down menu.
                <p>
                    2.  You can view or edit the details of a record, or delete the record entirely, by clicking on the appropriate links within each category.
                </p>
                <p>
                    3.  You have the ability to upload pictures to the application!  To upload a sighting picture, click on the sightings tab and complete the
                    form to add a picture.  To upload a profile picture, click on the "Add a Profile Picture!" tab and complete the form.  You can view all of your
                    uploaded photos by clicking on the "Picture Gallery" tab.
                </p>
                <p>
                    Enjoy!
                </p>
            </div>
            <div class="col-md-6">
                <h3 style = "text-align:center"><i>The 10 Most Recent Sightings!</i></h3>
                <table class = "table table-striped">
                    <thead>
                    <th>Sighting</th>
                    <th>Sighting Date</th>
                    </thead>
                    <tbody>
                        <c:forEach var="recentList" items="${recentList}">
                            <tr>
                                <td>${recentList.sightingName}</td>
                                <td>${recentList.sightingDate}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <script src="${pageContext.request.contextPath}/js/jquery-3.1.1.min.js"></script>
        <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    </body>
</html>

