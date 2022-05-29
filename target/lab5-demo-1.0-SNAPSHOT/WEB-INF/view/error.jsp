<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${userLocale}"/>
<fmt:setBundle basename="bundle"/>
<html>
<head>
    <title><fmt:message key="title.error"/></title>
    <%@include file="include/header.jsp" %>
</head>
<header>
    <%@include file="include/menu.jsp"%>
    <h1><fmt:message key="title.error"/></h1>
</header>
<body>
<p>
    <fmt:message key="msg.desc"/>: ${exception.message}
</p>
<p>
    ${exception.cause.message}
</p>
</body>
<footer>
    <%@include file="include/footer.jsp" %>
</footer>
</html>
