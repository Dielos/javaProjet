<%-- 
    Document   : controleur
    Created on : 18 avr. 2016, 15:51:00
    Author     : Dielos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
    <c:choose>        
        <c:when test="${param.action == 'homepage'}">
            <jsp:forward page="view/homepage.jsp"/>
	</c:when>
        
        <c:when test="${param.action == 'order'}">
            <jsp:forward page="view/order.jsp">
                <jsp:param name="number" value="${number}"/>
            </jsp:forward>
	</c:when>
        
        <c:when test="${param.action == 'process'}">
            <jsp:forward page="view/process.jsp"/>
	</c:when>
        
        <c:when test="${param.action == 'stats'}">
            <jsp:forward page="view/stats.jsp"/>
	</c:when>
        
        <c:when test="${param.action == 'timeline'}">
            <jsp:forward page="view/timeline.jsp"/>
	</c:when>
        
        <c:otherwise>
            <jsp:forward page="view/homepage.jsp"/>
        </c:otherwise>
    </c:choose>
	
</html>
