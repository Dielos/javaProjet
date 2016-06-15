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
        <script>
            /*$(document).ready(function() {
                $('nav li').click(function() { return false; });
                $('#selectInstance').click(function(e){
                    if($( "#selectInstance option:selected" ).text()===$( "#selectInstance option:first" ).text()){
                        $('.disable').bind("click.myclick",(function(e){
                             e.preventDefault();
                        });
                    }
                    else{
                         $('.disable').unbind(".myclick");
                    }
                });
            } );*/
        </script>
         
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet"/>
        <link href="css/style.css" rel="stylesheet"/>
        <title>Homepage</title>

    </head>
    <body>
        <c:set var="instanceName" value="${instanceName}"/>
        <nav class="navbar navbar-inverse">
          <!-- Collect the nav links, forms, and other content for toggling -->
          <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
              <li class="active"><a class="disable" href="controller?action=homepage&instanceName=${instanceName}">HomePage<span class="sr-only">(current)</span></a></li>
              <li><a class="disable" href="controller?action=process&instanceName=${instanceName}">Process</a></li>
              <li><a class="disable" href="controller?action=stats&instanceName=${instanceName}">Stats</a></li>
              <li><a class="disable" href="controller?action=timeline&instanceName=${instanceName}">Timeline</a></li>
              <li class="dropdown">
                <a class="disable" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Orders<span class="caret"></span></a>
                <ul class="dropdown-menu">
                    
                    <c:forEach var="order" items="${navOrders}">
                        <li><a class="disable" href="controller?action=order&id=${order.getId()}&instanceName=${instanceName}">
                                <c:out value="${order.getOrderName()}"/>
                            </a>
                        </li>
                    </c:forEach>
                        
                </ul>
              </li>
              <li id="instanceName"><a href="#">${instanceName}</a></li>
            </ul>
          </div><!-- /.navbar-collapse -->
      </nav>
        
        <div class ="container" id="homepage">
            
            <div class="row">
                <select id="selectInstance" class="form-control" onChange="window.location.href=this.value">
                    <option class="option" value="#">Veuillez choisir une instance à charger ou uploader un fichier</option>
                    <c:forEach var="instanceName2" items="${instances}">
                        <option class="option" value="controller?action=homepage&instanceName=${instanceName2.getInstanceName()}">${instanceName2.getInstanceName()}</option>
                    </c:forEach>
                </select>
            </div>
    
            <div class="row">
                <h3><c:out value="${text}"/></h3>
            </div>
            
            <form action="controller" method="post" enctype="multipart/form-data">
                <div class="row">   
                        <input class="btn btn-lg btn-primary submit-button" type="file" name="file"/>

                    <div class="row">
                        <input class="btn btn-lg btn-primary submit-button" type="submit" value="OK"/>
                    </div>
                </div>
            </form>

            <div class="row">
                <a class="btn btn-lg btn-link link-to-download" href="controller?action=download&instanceName=${instanceName}">Download the solution</a>
            </div>
        </div>
        
    </body>
</html>
