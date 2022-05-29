<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <nav class="main-menu">
        <ul>
            <li><a href="${pageContext.request.contextPath}/serv?action=index"
            ><fmt:message key="nav.index"/></a></li>
            <li><a href="${pageContext.request.contextPath}/serv?action=users"
            ><fmt:message key="nav.users"/></a></li>
            <li><a href="${pageContext.request.contextPath}/serv?action=positions"
            ><fmt:message key="nav.menu"/></a></li>
            <li><a href="${pageContext.request.contextPath}/serv?action=orders"
            ><fmt:message key="nav.orders"/></a></li>
            <li><a href="${pageContext.request.contextPath}/serv?action=contacts"
            ><fmt:message key="nav.contacts"/></a></li>
            <li><a href="${pageContext.request.contextPath}/serv?action=chat"
            ><fmt:message key="nav.chat"/></a></li>
            <li><a href="${pageContext.request.contextPath}/serv?action=openLogin"
            ><fmt:message key="nav.login"/></a></li>
            <li>
                <%@include file="/WEB-INF/view/language_chooser.jsp" %>
            </li>
        </ul>
    </nav>