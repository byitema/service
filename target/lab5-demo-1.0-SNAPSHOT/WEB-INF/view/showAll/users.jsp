<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--@elvariable id="userLocale" type="java.util.Locale"--%>
<fmt:setLocale value="${userLocale}"/>
<fmt:setBundle basename="bundle"/>
<html>
<head>
    <title><fmt:message key="nav.users"/></title>
    <%@include file="../include/header.jsp" %>
</head>
<body>

<%@include file="../include/menu.jsp" %>
<header>
    <h1><fmt:message key="title.rest"/></h1>
    <h2><fmt:message key="nav.users"/></h2>
</header>

<%--@elvariable id="users" type="java.util.List<org.bsu.web.lab8.model.entity.User>"--%>
<c:if test="${!empty users}">
    <div class="page-table">
        <table class="table">
            <thead>
            <tr>
                <th>ID</th>
                <th><fmt:message key="table.uname"/></th>
                <th><fmt:message key="table.isAdmin"/></th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${users}" var="user">
                <tr>
                    <td>${user.id}</td>
                    <td>${user.nickname}</td>
                    <td>${user.admin}</td>
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
