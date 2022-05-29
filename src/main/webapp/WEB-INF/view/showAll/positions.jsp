<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--@elvariable id="userLocale" type="java.util.Locale"--%>
<fmt:setLocale value="${userLocale}"/>
<fmt:setBundle basename="bundle"/>
<html>
<head>
    <title><fmt:message key="nav.menu"/></title>
    <%@include file="../include/header.jsp" %>
</head>
<body>
<%@include file="../include/menu.jsp"%>
<header>
    <h1><fmt:message key="title.rest"/></h1>
    <h2><fmt:message key="nav.menu"/></h2>
</header>

<%--@elvariable id="positionsList" type="java.util.List<org.bsu.web.lab8.model.entity.Position>"--%>
<c:if test="${!empty positionsList}">
    <div class="page-table">
        <table class="table">
            <thead>
            <tr>
                <th>ID</th>
                <th><fmt:message key="table.name"/></th>
                <th><fmt:message key="table.cost"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${positionsList}" var="position">
                <tr>
                    <td>${position.id}</td>
                    <td>${position.itemName}</td>
                    <td>${position.cost}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</c:if>
</body>
<footer>
    <%@include file="../include/footer.jsp" %>
</footer>
</html>
