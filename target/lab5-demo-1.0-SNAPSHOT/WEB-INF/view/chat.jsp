<%@ page pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:requestEncoding value="UTF-8"/>
<%--@elvariable id="userLocale" type="java.util.Locale"--%>
<fmt:setLocale value="${userLocale}"/>
<fmt:setBundle basename="bundle"/>

<!DOCTYPE html>
<html>
<head>
    <%@include file="include/header.jsp" %>
    <title><fmt:message key="nav.chat"/></title>
    <script src="${pageContext.request.contextPath}/script/chat.js"></script>
</head>
<body>
<%@include file="include/menu.jsp" %>
<h1><fmt:message key="nav.chat"/></h1>

<input type="hidden" id="senderId" value="${senderId}">
<input type="hidden" id="isAdmin" value="${isAdmin}">
<div>
    <c:if test="${sessionScope['usertype'] == 'admin'}">
        <p>
            <fmt:message key="msg.adminChat"/>
        </p>
    </c:if>
    <textArea id="chatWindow" rows="10" style="width: 90%;margin: 15px" readonly></textArea>
</div>
<div>
    <input type="text" id="chatInput" style="width: 40em;margin: 15px"/>
    <input id="sendBtn"
           type="button"
           class="btn btn-primary"
           value="<fmt:message key="btn.send"/>"
           onclick="sendMessage()"/>
</div>
<script>lang = "${userLocale.language}"</script>
</body>
<footer>
    <%@include file="include/footer.jsp" %>
</footer>
</html>