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
            <c:forEach items="${order.getProductInBox()}" var="entry">
                <div class="row">
                    <div class="col-2">
                     <c:out value="Box ${entry.key.getBoxType().getBoxName()}_${entry.key.getNum()}"/>
                    </div>
                </div>
                <div class="row">
                    <div class="col-10">
                        <div id="${entry.key.getNum()}" style="position:relative; width: ${entry.key.getBoxType().getWidth()}px; height: ${entry.key.getBoxType().getHeight()}px; border:black 1px solid; background-color:pink;">
                            <c:set var="y" value="${0}"/>
                            <c:forEach items="${entry.value}" var="entry2">

                                 <c:set var="maxEmpile" value="${entry2.key.getEmpileMax(entry.key.getBoxType())}"/>
                                 <c:set var="cpt" value="${0}"/>
                                 <c:set var="x" value="${0}"/>

                                 <c:forEach items="${entry2.value}" var="product">
                                     <c:if test="${cpt != maxEmpile}">
                                         <div class="productBox" style="position:absolute; 
                                              background-color: ${colors[entry2.key.getId()%136]};
             bottom:${x}px; left:${y}px; border:black 1px solid; width:
             ${product.getTypeProduct().getWidth()}px; height:${product.getTypeProduct().getHeight()}px">
                                             <c:set var="x" value="${product.getTypeProduct().getHeight()+x}"/>
                                         </div>
                                     </c:if>
                                     <c:if test="${cpt == maxEmpile}">
                                         <c:set var="x" value="${0}"/>
                                         <c:set var="cpt" value="${0}"/>
                                         <c:set var="y" value="${entry2.key.getWidth()+y}"/>
                                         <div class="productBox" style="position:absolute; 
                                              background-color: ${colors[entry2.key.getId()%136]};
             bottom:${x}px; left:${y}px; border:black 1px solid; width:
             ${product.getTypeProduct().getWidth()}px; height:${product.getTypeProduct().getHeight()}px">
                                             <c:set var="x" value="${product.getTypeProduct().getHeight()+x}"/>
                                         </div>
                                     </c:if>
                                     <c:set var="cpt" value="${cpt=cpt+1}"/>
                                 </c:forEach>
                                 <c:set var="y" value="${entry2.key.getWidth()+y}"/>
                             </c:forEach>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </div>
    </body>
</html>
