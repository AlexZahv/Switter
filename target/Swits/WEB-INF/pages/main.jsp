<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"  %>
<html>
<head>
    <title>Share your favorite swits</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/switter.js"/>
    <script src="${pageContext.request.contextPath}/resources/js/jquery-3.1.0.js" type="text/javascript"
            charset="utf-8"/>
    <script type="text/javascript">
        function addNewComment() {
            if ($("#new_comment").is(":hidden"))
                $("#new_comment").css('display', 'inline-block');
            else  $("#new_comment").hide();
        }
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
        <div class="swit" style="background-color: #36b9ff;height: 20px;border: none;">
            <div>
                <input id="new_swit_button" type="button"
                       onclick="add_new_swit(this)" value="New swit"/>
            </div>

        </div>
        <div id="new_swit" style="display: none">
            <form action="publishing" method="post" id="new_swit_form" style="">
                <label class="info_label" style="clear: both">Message:</label>
                <textarea name="messageBody" id="message_body" style="resize: none;  width: 100%;"
                          placeholder="write your swit here"></textarea>
                <button type="submit" class="submit_button" onclick="return check_new_swit(event);"><spring:message code="button.submit"/> </button>
            </form>
        </div>
        <div class="clear" style="height: 10px"></div>
        <c:forEach items="${swits}" var="swit">
            <div class="swit">
                <div class="avatar">
                    <a href="personal_page?login=${swit.author.login}"> <img src="imageController/${swit.author.login}"
                            style="width: 80px;height: 80px;border: solid #000000 1px;border-radius: 4px;float: left;"
                            class="avatar-image"></a>

                    <div class="user-info" style="float: left;margin-top: 5px;font-size: 14px;text-align: center">
                        <div style="float: left;margin-left: 0">${swit.author.name} </div>
                    </div>
                </div>
                <div class="message">
                    <label class="message_body">
                            ${swit.messageBody}
                    </label>

                </div>

                <div class="clear" style="height: 1px"></div>
                <c:if test="${comments!=null}">
                    <hr>
                    <h3>Comments:</h3>

                    <c:forEach items="${comments}" var="comment">
                        <div class="comment" style="margin-left: 20%">
                            <div class="avatar">
                                <a href="personal_page?login=${comment.author.login}"> <img
                                        src="/imageController/${swit.author.login}"
                                        style="width: 80px;height: 80px;border: solid #000000 1px;border-radius: 4px;float: left;"
                                        class="avatar-image"></a>

                                <div class="user-info"
                                     style="float: left;margin-top: 5px;font-size: 14px;text-align: center">
                                    <div style="">${comment.author.name} </div>
                                    <div style="clear: left">${comment.author.surname}</div>
                                </div>
                            </div>
                            <div class="message">
                                <label class="message_body">
                                        ${comment.content}
                                </label>

                            </div>
                        </div>
                        <div id="new_comment" style="display: none">
                            <form action="publishing" method="post" id="new_comment_form" style="">
                                <label class="info_label" style="clear: both">Message:</label>
                <textarea name="content" id="comment_body" style="resize: none;  width: 100%;"
                          placeholder="write your swit here"></textarea>
                                <input type="submit" value="Submit" class="submit_button"
                                       onclick="">
                            </form>
                        </div>
                    </c:forEach>
                </c:if>
                <form class="more-form">
                    <input value="Comment" class="swit_button" name="butt" type="button" onclick=""
                           style="clear: both;float: right"/>
                </form>
            </div>
            <br>

            <div class="clear" style="height: 10px"></div>
        </c:forEach>


    </div>
    <div id="footer">
    </div>


</div>
<!-- end of main_container -->

</body>
</html>
