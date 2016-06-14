<%-- 
    Document   : Timeline
    Created on : 12 mai 2016, 09:27:24
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
        <title>Timeline</title>
    </head>
    <body>
        <nav class="navbar navbar-inverse">
          <!-- Collect the nav links, forms, and other content for toggling -->
          <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
              <li><a href="controller?action=homepage">HomePage<span class="sr-only">(current)</span></a></li>
              <li><a href="controller?action=process">Process</a></li>
              <li><a href="controller?action=stats">Stats</a></li>
              <li class="active"><a href="controller?action=timeline">Timeline</a></li>
              <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Orders<span class="caret"></span></a>
                <ul class="dropdown-menu">
                  <li><a href="controller?action=order&number=1">Order 1</a></li>
                  <li><a href="controller?action=order&number=2">Order 2</a></li>
                  <li><a href="controller?action=order&number=3">Order 3</a></li>
                  <li><a href="controller?action=order&number=4">Order 4</a></li>
                </ul>
              </li>
            </ul>
          </div><!-- /.navbar-collapse -->
        </nav>
       
        ${text}
        
        
        
        <c:forEach items="${lines}" var="line">
            <label>Line ${line.getId()}</label>
            </br>
            <div  style="margin: 0 0 0 20px; display:inline-block;"></div>
            <c:forEach items="${instances}" var="instance">
                <div  style="margin: 0 0 0 -5px; width: 30px; height: 20px; display:inline-block;background: ${colors[0]}; border:1px solid black "></div>
            </c:forEach>
            </br>
        </c:forEach>
            
           
            
    </body>
</html>
