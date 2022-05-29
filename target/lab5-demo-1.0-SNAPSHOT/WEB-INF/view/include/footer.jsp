<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<p>
    <fmt:message key="user.date"/> ${cookie['lastEnterTime'].value}
</p>
<p>
    <fmt:message key="user.visits"/> ${cookie['usageCount'].value}
</p>
<script src="${pageContext.request.contextPath}/script/script.js"></script>
<script
        src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-ygbV9kiqUc6oa4msXn9868pTtWMgiQaeYH7/t7LECLbyPA2x65Kgf80OJFdroafW"
        crossorigin="anonymous"
></script>