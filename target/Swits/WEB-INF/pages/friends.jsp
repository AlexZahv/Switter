<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Share your favorite swits</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/switter.js"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery-3.1.0.js"/>
    <script type="text/javascript">
        $(document).ready(function () {
            if ($("#indicator_label").text() != "owner")
                $("#new_swit_button").hide();
            else
                $("#new_swit_button").css('display', 'inline-block');
        });
    </script>

</head>
<body>

<div id="main_container">
    <div id="header">
        <div class="logo"></div>
    </div>
    <div class="menu">
        <ul>
            <li class="selected"><a href="personal_page"><spring:message code="menu.home"/> </a></li>
            <li><a href="main"><spring:message code="menu.news"/> </a></li>
            <li><a href="friends"><spring:message code="menu.followers"/> </a></li>
            <li><a href="#"><spring:message code="menu.search"/> </a></li>
            <li><a href="#">FAQ</a></li>
            <li><a href="#"><spring:message code="menu.help"/> </a></li>
            <li><a href="logout"><spring:message code="menu.logout"/> </a></li>
        </ul>
    </div>

    <div class="center_content">
        <div class="friends_container">
            <h1 style="text-align: center;color: #000000">${title}</h1>
            <c:forEach items="${persons}" var="person">
                <hr>
                <div style="display: inline-block;float: left;">
                    <a href="personal_page?login=${person.login}"> <img
                            src="imageController/${person.login}"
                            style="width: 80px;height: 80px;border: solid #000000 1px;border-radius: 4px;float: left;"
                            id="avatar-image"></a>

                    <div style="display:inline-block;margin-left: 15px">
                        <div style="float: left;font-size: 14px">
                            <a href="personal_page?login=${person.login}"><h4
                                    class="name_friends_list">${person.name} ${person.surname}</h4></a>
                        </div>
                        <div style="float: left;font-size: 14px;clear: both">
                            <div class="clear" style="height: 10px"></div>
                            <div style="clear: both;float: left">Info:</div>
                            <div class="clear" style="height: 10px"></div>
                            <div style="clear: both;float: left">Country: ${person.country}</div>
                            <div class="clear" style="height: 10px"></div>
                            <div style="clear: both;float: left">City: ${person.city}</div>
                        </div>
                    </div>
                </div>

                <img src="<%=pageContext.getServletContext().getContextPath()%>/resources/img/message.png"
                     style="width: 50px;height: 50px;border-radius: 50%;float: right;top: 50%">

                <div class="clear" style="height: 10px"></div>
            </c:forEach>
        </div>
    </div>
</div>
<div id="footer">
</div>

</div>
<!-- end of main_container -->

</body>
</html>
