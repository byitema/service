<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="main" uri="/WEB-INF/mytaglib.tld" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${userLocale}"/>
<fmt:setBundle basename="bundle"/>

<html>
<head>
    <title><fmt:message key="title.clientOrders"/></title>
    <%@include file="include/header.jsp" %>
</head>
<body>
<%@include file="include/menu.jsp" %>
<header>
    <h1><fmt:message key="title.rest"/></h1>
    <h2><fmt:message key="title.clientOrders"/></h2>
</header>

<form
        id="get-orders-form"
        action="${pageContext.request.contextPath}/serv"
        method="get"
>
    <input type="hidden" name="action" value="clientOrders">
    <div>
        <label for="clientID"><fmt:message key="msg.enterClientID"/></label>
        <input id="clientID" type="number" step="1" min="1" name="clientID">
    </div>
    <input type="submit" value=<fmt:message key="btn.go"/>>
</form>

<c:if test="${!empty clientOrders}">
    <div class="page-table">
        <main:tableTag
                orders="${clientOrders}"
                admin="${sessionScope['usertype'] == 'admin'}"
        />
    </div>
</c:if>
</body>
<footer>
    <%@include file="include/footer.jsp" %>
</footer>
</html>
