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
       
        ${text}
        
        <c:forEach items="${lines}" var="line">
            <label>Line ${line.getId()}</label>
                <canvas id="${line.getId()}" width="3000" height="15" style="border:1px solid #d3d3d3; background-color: black"> 
                    Your browser does not support the HTML5 canvas tag.
                </canvas>
        </c:forEach>
            
            
        <script>
            
        function addLine(begin, length, color, line) {
          var lineNum = line;
          var c = document.getElementById(lineNum);
          var ctx = c.getContext("2d");
          ctx.fillStyle = 'black';
          ctx.beginPath();
          ctx.rect(begin, 0, length, 15);
          ctx.fillStyle = color;
          ctx.fill();
          ctx.stroke();
        }
        
        var val
        
        for(val of ${lines})
            addLine(10, 15, 'red', val.getId());
            addLine(25, 25, 'yellow', val.getId());
            addLine(60, 10, 'blue', val.getId());

            addLine(0, 30, 'green', val.getId());
            addLine(45, 10, 'red', val.getId());
            addLine(55, 5, 'blue', val.getId());
        
        </script> 
            
            
    </body>
</html>
