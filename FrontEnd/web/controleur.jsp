<%-- 
    Document   : controleur
    Created on : 18 avr. 2016, 15:51:00
    Author     : Dielos
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <c:choose>        
        <c:when page="${param.action == 'homepage'}">
            <jsp:forward page="view/homepage.jsp"/>
	</c:when>
        
        <c:when page="${param.action == 'order'}">
            <%String number = request.getParameter("number");%>
            <%String link = "view/order.jsp?num=${number}";%>
            <jsp:forward page="${link}">
                <jsp:param name="number" value="${number}"/>
            </jsp:forward>
	</c:when>
        
        <c:when page="${param.action == 'process'}">
            <jsp:forward page="view/process.jsp"/>
	</c:when>
        
        <c:when page="${param.action == 'strats'}">
            <jsp:forward page="view/strats.jsp"/>
	</c:when>
        
        <c:when page="${param.action == 'timeline'}">
            <jsp:forward page="view/timeline.jsp"/>
	</c:when>
        
        <c:otherwise>
            <jsp:forward page="view/homepage.jsp"/>
        </c:otherwise>
    </c:choose>
	
</html>
