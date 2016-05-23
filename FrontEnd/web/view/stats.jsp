<%-- 
    Document   : newjspstats
    Created on : 12 mai 2016, 09:27:07
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
        <title>Stats</title>
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
                  <li><a href="controleur.jsp?action=order&number=1">Order 1</a></li>
                  <li><a href="controleur.jsp?action=order&number=2">Order 2</a></li>
                  <li><a href="controleur.jsp?action=order&number=3">Order 3</a></li>
                  <li><a href="controleur.jsp?action=order&number=4">Order 4</a></li>
                </ul>
              </li>
            </ul>
          </div><!-- /.navbar-collapse -->
      </nav>
        <table style="margin-left:  5%;width:90%">
            <tr>
                <td style="width:25%;text-align:center;background-color: #ffcc00;border:3px solid white">Type de box</td>                
                <td style="width:17%;text-align:center;background-color: #ffcc00;border:3px solid white" >Prix</td>                
                <td style="width:17%;text-align:center;background-color: #ffcc00;border:3px solid white" >Achat</td>                
                <td style="width:17%;text-align:center;background-color: #ffcc00;border:3px solid white" >Utilisés</td>                
                <td style="width:22%;text-align:center;background-color: #ffcc00;border:3px solid white" >Coût</td>
            </tr>
            <tr>
                <td style="width:25%;text-align:center;border:3px solid white">AAA</td>                
                <td style="width:17%;text-align:center;border:3px solid white" >222</td>                
                <td style="width:17%;text-align:center;border:3px solid white" >3</td>                
                <td style="width:17%;text-align:center;border:3px solid white" >2</td>                
                <td style="width:22%;text-align:center;border:3px solid white" >666</td>
            </tr>
        </table> 
        
        
        
        
    </body>
</html>
