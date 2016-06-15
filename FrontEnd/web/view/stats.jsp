<%-- 
    Document   : newjspstats
    Created on : 12 mai 2016, 09:27:07
    Author     : Dielos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<sql:setDataSource var="snapshot" driver="com.mysql.jdbc.Driver"
     url="jdbc:derby://localhost:1527/db_project"
     user="test"  password="test"/>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script type="text/javascript" src="js/jquery.js"></script>
        <script type="text/javascript" src="js/bootstrap.min.js"></script>
        <script type="text/javascript" src="https://cdn.datatables.net/1.10.12/js/jquery.dataTables.min.js"></script>
        <script type="text/javascript" src="https://cdn.datatables.net/1.10.12/js/dataTables.bootstrap.min.js"></script>
        
        <script type="text/javascript" src="js/script.js"></script>
        <script>
            $(document).ready(function() {
                $('.table').dataTable();
            } );
        </script>
        <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet"/>
        <link href="https://cdn.datatables.net/1.10.12/css/dataTables.bootstrap.min.css" rel="stylesheet"/>
        <title>Stats</title>
    </head>
    <body>
    
    <nav class="navbar navbar-inverse">
          <!-- Collect the nav links, forms, and other content for toggling -->
          <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
            <ul class="nav navbar-nav">
              <li><a href="controller?action=homepage">HomePage<span class="sr-only">(current)</span></a></li>
              <li><a href="controller?action=process">Process</a></li>
              <li class="active"><a href="controller?action=stats">Stats</a></li>
              <li><a href="controller?action=timeline">Timeline</a></li>
              <li class="dropdown">
                <a class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Orders<span class="caret"></span></a>
                <ul class="dropdown-menu">
                    
                    <c:forEach var="order" items="${navOrders}">
                        <li><a href="controller?action=order&id=${order.getId()}">
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
            <div class="row">
                <h1>Statistiques sur la solution de l'instance <c:out value="${instanceName}"/></h1>
            </div>
            <div class="row">
                <div class="col-8"></div>
                <div class="col-2 text-right"><b>Total coût instance:</b> ${instance.getTotalCost()}€</div>
                <div class="col-2 text-right"></div>
            </div>
            <div class="row">
                <div class="col-8"></div>
                <div class="col-2 text-right"><b>Total coût boxs: </b>${instance.getBoxCost()}€</div>
                <div class="col-2 text-right"></div>
            </div>
            <div class="row">
                <div class="col-8"></div>
                <div class="col-2 text-right"><b>Total coût commandes: </b>${instance.getOrderCost()}€</div>
                <div class="col-2 text-right"></div>
            </div>
            <div class="row">
                <table class="table table-hover dataTable info">
                    <thead>
                        <tr>
                            <th class="warning col-4">Type de box</th>
                            <th class="warning col-2">Prix</th>
                            <th class="warning col-2">Achat</th>
                            <th class="warning col-2">Utilisés</th>
                            <th class="warning col-2">Coût</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="boxType" items="${boxTypes}">
                            <tr>
                                <td>
                                    <c:out value="${boxType.boxName}"/>
                                </td>
                                <td>
                                    <c:out value="${boxType.cost}"/>
                                </td>
                                <td>
                                    <c:out value="${boxType.getBoxs().size()}"/>
                                </td>
                                <td>
                                    <c:out value="${boxType.getBoxs().size()}"/>
                                </td>
                                <td>
                                    <c:out value="${boxType.getTotalBoxesCost()}€"/>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <div class="row">
                <table class="table table-hover dataTable info">
                    <thead>
                        <tr>
                            <th class="warning col-2">Commande</th>
                            <th class="warning col-2">Envoi prévu</th>
                            <th class="warning col-2">Envoi</th>
                            <th class="warning col-1">Pénalité</th>
                            <th class="warning col-1">Ecart</th>
                            <th class="warning col-2">Nombre de box</th>
                            <th class="warning col-2">Coût</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="order" items="${orders}">
                            <tr>
                                <td>
                                    <c:out value="${order.getOrderName()}"/>
                                </td>
                                <td>
                                    <c:out value="${order.getDateLimit()}"/>
                                </td>
                                <td>
                                    <c:out value="${order.getSendingDate()}"/>
                                </td>
                                <td>
                                    <c:out value="${order.getPenality()}"/>
                                </td>
                                <td>
                                    <c:out value="${order.getSendingDate()-order.getDateLimit()}"/>
                                </td>
                                <td>
                                    <c:out value="${order.getBoxs().size()}"/>
                                </td>
                                <td>
                                    <c:out value="${order.getPenalityCost()}€"/>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
