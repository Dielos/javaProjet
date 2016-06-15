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
        <link href="css/style.css" rel="stylesheet"/>
        <title>Order</title>
    </head>
    <body>
    <nav class="navbar navbar-inverse">
          <!-- Collect the nav links, forms, and other content for toggling -->
          <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
              <li><a href="controller?action=homepage&instanceName=${instanceName}">HomePage<span class="sr-only">(current)</span></a></li>
              <li><a href="controller?action=stats&instanceName=${instanceName}">Stats</a></li>
              <li><a href="controller?action=timeline&instanceName=${instanceName}">Timeline</a></li>
              <li class="dropdown active">
                <a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Orders<span class="caret"></span></a>
                <ul class="dropdown-menu">
                    <c:forEach var="order" items="${navOrders}">
                        <li><a href="controller?action=order&id=${order.getId()}&instanceName=${instanceName}">
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
    <div class="container">
        <div class ="row"
            <h1> Commande <c:out value="${order.getOrderName()}"/> prévue à <c:out value="${order.getDateLimit()}"/> envoyée à <c:out value="${order.getSendingDate()}"/></h1>
            <h2>Production</h2>
        </div>

        <div class="row">
            <h2>Utilisation des box</h2>
            <c:forEach var="box" items="${order.getBoxs()}">
                <div class="row">
                    <div class="col-2">
                        <c:set var="margin" scope="session" value="0"/>
                        <c:out value="Box ${box.getBoxType().getBoxName()}_${box.getNum()}"/>
                    </div>
                    <div class="col-10">
                        <div id="${box.getNum()}" style="width: ${box.getBoxType().getWidth
()}px; height: ${box.getBoxType().getHeight()}px; border:black 1px solid; background-
color:pink;">
                            <c:forEach var="orderLine" items="${order.getOrderLines()}">
         
                                <c:forEach  var="product" items="${orderLine.getProducts()}">
                                    <c:if test="${product.getBox().getBoxType().getBoxName()
==box.getBoxType().getBoxName()}">
                                        <c:if test="${product.getBox().getNum()==box.getNum
()}">
                                            <div class="productBox" style="float:left; 
background-color: white;  border:black 1px solid; width:
${product.getTypeProduct().getWidth()}px; height:${product.getTypeProduct().getHeight()}px">
                                            </div>
                                        </c:if>
                                    </c:if>
                                </c:forEach>
                            </c:forEach>
                            <c:forEach items="${order}" var="entry">
                                Key = ${entry.key}, value = ${entry.value}<br>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </div>
    </body>
</html>
