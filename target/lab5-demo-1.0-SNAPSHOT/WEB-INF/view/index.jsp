<%@ page pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${userLocale}"/>
<fmt:setBundle basename="bundle"/>
<html>
<head>
    <title><fmt:message key="nav.index"/></title>
    <%@include file="include/header.jsp" %>
</head>
<body>
<%@include file="include/menu.jsp"%>
<header>
    <h1><fmt:message key="title.rest"/></h1>
</header>
<p>
    <fmt:message key="par.description"/>

</p>
<p>
    <fmt:message key="par.abilities"/>

</p>

    <p><a href="${pageContext.request.contextPath}/serv?action=openClientOrders"
    ><fmt:message key="par.clientOrders"/></a></p>
    <p><a href="${pageContext.request.contextPath}/serv?action=positions"
    ><fmt:message key="par.menu"/></a></p>
    <p><a href="${pageContext.request.contextPath}/serv?action=confirmedOrders"
    ><fmt:message key="par.confirmedOrders"/></a></p>
    <p><a href="${pageContext.request.contextPath}/serv?action=makeOrder"
    ><fmt:message key="par.makeOrder"/></a></p>
    <p><a href="${pageContext.request.contextPath}/serv?action=confirmOrder"
    ><fmt:message key="par.confirmOrder"/></a></p>

</body>
<footer>
    <%@include file="include/footer.jsp" %>
</footer>
</html>
