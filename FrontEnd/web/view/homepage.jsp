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
        <script type="text/javascript" src="js/script.js"></script>
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet"/>
        <title>Homepage</title>
    </head>
    <body>
        <nav class="navbar navbar-inverse">
          <!-- Collect the nav links, forms, and other content for toggling -->
          <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
              <li><a href="controleur.jsp?action=homepage">HomePage<span class="sr-only">(current)</span></a></li>
              <li><a href="controleur.jsp?action=process">Process</a></li>
              <li class="active"><a href="controleur.jsp?action=stats">Stats</a></li>
              <li><a href="controleur.jsp?action=timeline">Timeline</a></li>
              <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Orders<span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <c:forEach var="order" items="${navOrders}">
                        <li><a href="controleur.jsp?action=${order.getOrderName}">
                                <c:out value="${order.getOrderName}"/>
                            </a>
                        </li>
                    </c:forEach>
                        
                </ul>
              </li>
            </ul>
          </div><!-- /.navbar-collapse -->
      </nav>
        
        
        <a href="controller?action=download">Download link</a>
        
        
        
        <form action="controller" method="post" enctype="multipart/form-data">
            <input type="file" name="file" />
            <input type="submit" />
        </form>
    </body>
</html>
