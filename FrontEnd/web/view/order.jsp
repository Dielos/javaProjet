<%-- 
    Document   : order
    Created on : 12 mai 2016, 09:26:52
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
        <title>Order</title>
    </head>
    <body>
    <nav class="navbar navbar-inverse">
          <!-- Collect the nav links, forms, and other content for toggling -->
          <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
              <li><a href="controller?action=homepage">HomePage<span class="sr-only">(current)</span></a></li>
              <li><a href="controller?action=process">Process</a></li>
              <li><a href="controller?action=stats">Stats</a></li>
              <li><a href="controller?action=timeline">Timeline</a></li>
              <li class="dropdown active">
                <a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Orders<span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <c:forEach var="order" items="${navOrders}">
                        <li><a href="controller.jsp?action=order&name=${order.getOrderName()}">
                                <c:out value="${order.getOrderName()}"/>
                            </a>
                        </li>
                    </c:forEach>
                        
                </ul>
              </li>
            </ul>
          </div><!-- /.navbar-collapse -->
      </nav>
    <div class="container">
        <div class ="row"
            <h1> Commande <c:out value="${order.getOrderName()}"/> prévue à <c:out value="${order.getDateLimit()}"/> envoyée à <c:out value="${order.getSendingDate()}"/></h1>
            <h2>Production</h2>
        </div>

        <div class="row">
            <h2>Utilisation des box</h2>
            <c:forEach var="box" items="${order.getBoxes()}">
                <div class="row">
                    <c:out value="Box ${box.getBoxType().getBoxName()}_${box.getNum()}"/>
                </div>
            </c:forEach>
        </div>
    </div>
    </body>
</html>
