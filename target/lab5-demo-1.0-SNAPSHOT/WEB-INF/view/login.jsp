<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${userLocale}"/>
<fmt:setBundle basename="bundle"/>
<html>
<head>
    <title><fmt:message key="nav.login"/></title>
    <%@include file="include/header.jsp" %>
    <style>
        body{
            background:#191919;
            background-size: 100%;
            font-family: sans-serif;
        }

        .box{
            width: 300px;
            padding: 40px;
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            background: #191919;
            text-align: center;
            border-radius: 50px;
            border: 2px solid #0000FF;
            box-shadow: -20px 20px 20px #2E2977;


        }

        .box h1{
            color: #41343C;
            text-transform: uppercase;
            font-weight: 500;
        }

        .box h1:hover{
            color: #0000FF;
            font-weight: 700;
        }

        .box input[type = "text"],.box input[type = "password"] {
            background: none;
            display: block;
            margin: 20px auto;
            text-align: center;
            border: 2px solid #41343C;
            padding: 14px 10px;
            width: 200px;
            outline: none;
            color: white;
            border-radius: 24px;
            transition: 0.25s;

        }

        .box input[type = "text"]:focus ,.box input[type = "password"]:focus{
            width: 280px;
            border-color: #0000FF;
        }

        .box input[type= "submit"]{
            background: none;
            display: block;
            margin: 20px auto;
            text-align: center;
            border: 2px solid #0000FF;
            padding: 14px 40px;
            outline: none;
            color: #0000FF;
            border-radius: 24px;
            transition: 0.25s;
            cursor: pointer;

        }

        .box input[type= "submit"]:hover{
            color: white;
            background: #0000FF;
            font-weight: 600;
        }
    </style>

</head>
<body>
<%@include file="include/menu.jsp" %>
<div class="container-fluid">
    <div class="row main-content bg-success text-center">
        <div class="login_form ">
            <div class="container-fluid">
                <div class="row">
                    <form class="box" action="${pageContext.request.contextPath}/serv"
                          method="get">
                        <div class="row">
                            <input type="text" name="username" id="username" class="form__input"
                                   placeholder="<fmt:message key="placeholder.username"/>">
                        </div>
                        <div class="row">
                            <input type="password" name="password" id="password" class="form__input"
                                   placeholder="<fmt:message key="placeholder.password"/>">
                        </div>
                        <div class="row">
                            <input type="submit" value="login" class="btn" name="action">
                        </div>
                        <div class="row">
                            <input type="submit" value="signup" class="btn" name="action">
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
