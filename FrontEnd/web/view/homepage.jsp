<%-- 
    Document   : homepage
    Created on : 12 mai 2016, 09:26:45
    Author     : Dielos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="js/jquery.js"></script>
        <script type="text/javascript" src="js/bootstrap.min.js"></script>
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet"/>
        <link href="css/style.css" rel="stylesheet"/>
        <title>Homepage</title>

    </head>
    <body>
        <nav class="navbar navbar-inverse">
          <!-- Collect the nav links, forms, and other content for toggling -->
          <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
              <li class="active"><a href="controller?action=homepage">HomePage<span class="sr-only">(current)</span></a></li>
              <li><a href="controller?action=process">Process</a></li>
              <li><a href="controller?action=stats">Stats</a></li>
              <li><a href="controller?action=timeline">Timeline</a></li>
              <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Orders<span class="caret"></span></a>
                <ul class="dropdown-menu">
                    
                    <c:forEach var="order" items="${navOrders}">
                        <li><a href="controller?action=order&name=${order.getOrderName()}">
                                <c:out value="${order.getOrderName()}"/>
                            </a>
                        </li>
                    </c:forEach>
                        
                </ul>
              </li>
            </ul>
          </div><!-- /.navbar-collapse -->
      </nav>
        
        <div class ="container" id="homepage">
            <form action="controller" method="post" enctype="multipart/form-data">
              
                    <div class="row">
                        <h3><c:out value="${text}"/></h3>
                    </div>
                
                
                <div class="row">   
                        
                        <input class="btn btn-lg btn-primary submit-button" type="file" name="file"/>
                        
                    <div class="row">
                        <input class="btn btn-lg btn-primary submit-button" type="submit" value="OK"/>
                    </div>
                </div>
            </form>

            <div class="row">
                <a class="btn btn-lg btn-link link-to-download" href="controller?action=download">Download the solution</a>
            </div>
        </div>
        
    </body>
</html>
