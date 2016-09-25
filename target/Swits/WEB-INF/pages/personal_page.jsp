<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c"
           uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<html>
<head>
    <title>Share your favorite swits</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css"/>
    <script type="text/javascript" src="//ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/switter.js"/>
    <script src="${pageContext.request.contextPath}/resources/js/jquery-3.1.0.js" type="text/javascript"
            charset="utf-8"></script>
    <script type="text/javascript">
        $(document).ready(function () {
            $("#subscribed_preview").hide();
            $("#followers_preview").hide();
            if ($("#indicator_label").text() != "owner") {
                $("#new_swit_button").hide();
                $(".upload_avatar").hide();
                $("#follow_btn").css('display', 'inline-block');
            }
            else {
                $("#new_swit_button").css('display', 'inline-block');
                $(".upload_avatar").css('display', 'inline-block');
                $("#follow_btn").hide();
            }
        });

        function displayFollowers() {
            if ($( "#followers_preview").is( ":hidden" ))
                $("#followers_preview").css('display', 'inline-block');
            else $("#followers_preview").hide();
        }
        function displaySubscribed() {
            if ($( "#subscribed_preview").is( ":hidden" ))
                $("#subscribed_preview").css('display', 'inline-block');
            else $("#subscribed_preview").hide();
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
        <div class="personal_page_container"
             style="background-color:  #ffffff;border: solid 2px #000000;border-radius: 3px;">
            <div class="photo_container" style="float: left;width: 23%;margin: 10px">
                <img src="imageController/${account.login}"
                     id="profile_photo" style="width:100%;border: solid #000000 1px;border-radius: 4px;float: left;">
                <a href="addFriend?newFriendlogin=${account.login}">
                    <button class="swit_button" style="width: 100%;font-size: 14px;color: #000000" id="follow_btn">
                        Follow
                    </button>
                </a>

                <div class="clear" style="height: 10px"></div>
                <h2 id="followers_label" onclick="displayFollowers()">${followers.size()} Followers </h2>

                <div id="followers_preview" style="width: 100%;">
                    <c:forEach items="${followers}" var="follower">
                        <a href="personal_page?login=${follower.login}"> <img
                                src="imageController/${follower.login}"
                                style="" class="avatar_preview"></a>
                    </c:forEach>
                    <div class="clear" style="height: 10px"></div>
                    <a href="friends?login=${account.login}" style="text-decoration: none;font-size: 14px;">Show all</a>
                </div>
                <h2 id="subscribed_label" onclick="displaySubscribed()">${subscribed.size()} Subscribed </h2>

                <div id="subscribed_preview" style="width: 100%;">
                    <c:forEach items="${subscribed}" var="subscribed">
                        <a href="personal_page?login=${subscribed.login}"> <img
                                src="imageController/${subscribed.login}"
                                style="" class="avatar_preview"></a>
                    </c:forEach>
                    <div class="clear" style="height: 10px"></div>
                    <a href="subscribed?login=${account.login}" style="text-decoration: none;font-size: 14px;">Show all</a>
                </div>
                 <hr>

                <form method="POST" action="uploadFile" enctype="multipart/form-data" class="upload_avatar">
                    <h3 style="color: #000000;font-size: 14px;clear: both">Upload avatar</h3>
                    <input type="file" name="file" value="<spring:message code="input.choose_avatar" /> " style="width: 100%">
                    <input type="submit" value="<spring:message code="input.upload_avatar"/>" class="swit_button" style="width: 100%;font-size: 14px;color: #000000">

                </form>
            </div>
            <div class="account_details"
                 style="float: right;width: 70%;margin:10px;">
                <div class="name_details">
                    <b><c:out value="${account.name}"/> <c:out value="${account.surname}"/></b>
                </div>
                <div class="short_info" style="margin-top: 40px;text-align: center">
                    <hr>
                    <table style="margin: auto">
                        <tr>
                            <td class="person_props">Country</td>
                            <td class="clear_col"/>
                            <td class="person_props_details"><c:out value="${account.country}"/></td>
                        </tr>
                        <tr>
                            <td class="person_props">City</td>
                            <td class="clear_col"/>
                            <td class="person_props_details"><c:out value="${account.city}"/></td>
                        </tr>
                        <tr>
                            <td class="person_props">Email address</td>
                            <td class="clear_col"/>
                            <td class="person_props_details"><c:out value="${account.emailAddress}"/></td>
                        </tr>
                    </table>
                    <hr>
                    <p style="font-size: 16px;">
                        <b>About me: </b>
                    </p>

                    <p style="font-size: 14px"><c:out value="${account.shortDescription}"/></p>
                </div>
            </div>
            <div class="clear" style="height: 10px"></div>
            <hr>
            <form>
                <input id="new_swit_button" type="button" value="New swit"
                       style=""
                       onclick="add_new_swit(this);">
            </form>
        </div>
        <div class="clear" style="height: 10px"></div>
        <c:if test="${account.login==sessionScope.login}">
            <label id="indicator_label" style="display: none">owner</label>
        </c:if>

        <div id="new_swit" style="display: none">
            <form action="publishing" method="post" id="new_swit_form" style="">
                <label class="info_label" style="clear: both">Message:</label>
                <textarea name="messageBody" id="message_body" style="resize: none;  width: 100%;"
                          placeholder="write your swit here"></textarea>
                <input type="submit" value="Submit" class="submit_button" onclick="return check_new_swit(event);">
            </form>
        </div>
        <div class="clear" style="height: 10px"></div>
        <c:forEach items="${swits}" var="swit">
            <div class="swit">
                <div class="avatar">
                    <img src="imageController/${swit.author.login}"
                         style="width: 80px;height: 80px;border: solid #000000 1px;border-radius: 4px;float: left;"
                         class="avatar-image">

                    <div class="user-info">
                        <h5>${swit.author.name} ${swit.author.surname}</h5>
                    </div>
                </div>
                <div class="message">
                    <label class="message_body">
                            ${swit.messageBody}
                    </label>
                </div>
            </div>
            <br>
            <div class="clear" style="height: 10px"></div>
        </c:forEach>
    </div>
    <div id="footer">
    </div>
</div>

</body>
</html>
