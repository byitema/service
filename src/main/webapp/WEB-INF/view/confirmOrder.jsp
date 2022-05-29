<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${userLocale}"/>
<fmt:setBundle basename="bundle"/>
<html>
<head>
    <title><fmt:message key="title.confirmOrder"/></title>
    <%@include file="include/header.jsp" %>
</head>
<body>
<%@include file="include/menu.jsp" %>
<header>
    <h1><fmt:message key="title.rest"/></h1>
    <h2><fmt:message key="title.confirmOrder"/></h2>
</header>
<form
        id="confirm-order-form"
        action="${pageContext.request.contextPath}/serv"
        method="POST">
    <input type="hidden" name="action" value="confirmOrder">
    <div>
        <label for="orderID"><fmt:message key="msg.enterOrderID"/></label>
        <input id="orderID" type="number" step="1" min="1" name="orderID">
    </div>
    <input type="submit" value=<fmt:message key="btn.submit"/>>
</form>

<c:if test="${!empty unConfirmedOrders}">
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
            <c:forEach items="${unConfirmedOrders}" var="order">
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
    <%@include file="include/footer.jsp" %>
</footer>
</html>
