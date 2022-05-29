<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<meta charset="utf-8"/>
<link
        href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.0-beta1/dist/css/bootstrap.min.css"
        rel="stylesheet"
        integrity="sha384-giJF6kkoqNQ00vy+HMDP7azOuL0xtbfIcaT9wjKHr8RbDVddVHyTfAAsrekwKmP1"
        crossorigin="anonymous"
/>
<link rel="stylesheet" href="${pageContext.request.contextPath}/style/style.css"/>
<link rel="stylesheet"
      href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
      crossorigin="anonymous">
<style>
    * {
        -webkit-transition-property: all;
        -webkit-transition-duration: .2s;
        -moz-transition-timing-function: cubic-bezier(100,50,21,6);
        -moz-transition-property: all;
        -moz-transition-timing-function: cubic-bezier(100,50,21,6);
    }

    body {
        background:#191919;
        padding:75px;
        text-align:center;
        font-family: 'Oswald', sans-serif;
        color: white;
        margin: 0;
        font-weight: 100;
    }

    h1{
        color: white;
        font-weight:100;
    }

    .btn{
        color: white;
        background:#191919;
        padding:10px 20px;
        font-size:12px;
        text-decoration:none;
        letter-spacing:2px;
        text-transform:uppercase;
    }

    .btn:hover{
        border:none;
        background:#191919;
        background:#fff;
        padding:20px 20px;
        background:#191919;
    }

    html, body {
        height: 100%;
    }

    th, td {
        background:#191919;
        color: white;
    }

    th {
        padding-left: 50px;
        text-align: center;
    }

    tbody tr:hover {
        color: yellow;
    }

    tbody td:hover{
        color: yellow;
    }
</style>