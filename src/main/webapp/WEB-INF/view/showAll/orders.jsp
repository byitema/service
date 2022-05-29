<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" %>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--@elvariable id="userLocale" type="java.util.Locale"--%>
<fmt:setLocale value="${userLocale}"/>
<fmt:setBundle basename="bundle"/>
<html>
<head>
    <title><fmt:message key="nav.orders"/></title>
    <%@include file="../include/header.jsp" %>
</head>
<body>
<%@include file="../include/menu.jsp" %>
<header>
    <h1><fmt:message key="title.rest"/></h1>
    <h2><fmt:message key="nav.orders"/></h2>
</header>
<%--@elvariable id="orders" type="java.util.List<org.bsu.web.lab8.model.entity.Order>"--%>
<c:if test="${!empty orders}">
    <div class="page-table">
        <table class="table">
            <thead>
            <tr>
                <th>ID</th>
                <th><fmt:message key="table.date"/></th>
                <th><fmt:message key="table.isConfirmed"/></th>
                <th><fmt:message key="table.isPayed"/></th>
                <th><fmt:message key="table.clientID"/></th>
                <th><fmt:message key="table.adminID"/></th>
                <th><fmt:message key="table.positions"/></th>
                <th><fmt:message key="table.cost"/></th>
            </tr>
            </thead>
            <tbody>
                <%--@elvariable id="orderDao" type="org.bsu.web.lab8.model.dao.domain.OrderDao"--%>
            <c:forEach items="${orders}" var="order">
                <tr>
                    <td>${order.id}</td>
                    <td>${order.orderDate}</td>
                    <td>${order.confirmed}</td>
                    <td>${order.payed}</td>
                    <td>${order.client.id}</td>
                    <td>${order.admin.id}</td>
                    <td>
                        <c:forEach items="${order.positions}" var="orderPosition">
                            <p>
                                    ${orderPosition.position.itemName}: ${orderPosition.amount}
                            </p>
                        </c:forEach>
                    </td>
                    <td>${orderDao.getOrderCost(order.id)}</td>
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
