<%@ page pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${userLocale}"/>
<fmt:setBundle basename="bundle"/>
<html>
<head>
    <title><fmt:message key="nav.contacts"/></title>
    <%@include file="include/header.jsp" %>
</head>
<body>
<%@include file="include/menu.jsp" %>
<header>
    <h1><fmt:message key="title.rest"/></h1>
    <h2><fmt:message key="nav.contacts"/></h2>
</header>
<p>
    <fmt:message key="contact.me"/>
</p>
<p>
    <fmt:message key="contact.study"/>
</p>
<p>
    <fmt:message key="contact.year"/>
</p>
</body>
<footer>
    <%@include file="include/footer.jsp" %>
</footer>
</html>
