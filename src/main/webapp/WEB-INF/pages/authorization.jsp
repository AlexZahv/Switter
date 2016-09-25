<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"  %>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>Авторизация</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" />
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/styles.css" />
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/style.css" />
</head>
<body>
<div class="container">
    <div class="row">
        <div class="panel panel-primary" style="border: solid #000000 2px;">
            <div class="panel-body">
                <form method="POST" action="authorization" role="form" commandName="account">
                    <div class="form-group">
                        <h2><spring:message code="label.authorization"/> </h2>
                    </div>
                    <div class="form-group">
                        <label class="control-label" for="signupName"><spring:message code="label.login"/></label>
                        <input id="signupName" type="text" maxlength="50" class="form-control" name="login">
                    </div>
                    <div class="form-group">
                        <label class="control-label" for="signupPassword"><spring:message code="label.password"/></label>
                        <input id="signupPassword" type="password" maxlength="25" class="form-control" placeholder="" length="40" name="password">
                    </div>
                    <div class="form-group">
                        <button id="signupSubmit" type="submit" class="btn btn-info btn-block"><spring:message code="input.login"/></button>
                    </div>
                    <hr>
                    <p></p><spring:message code="label.no_account"/> <a href="registration"><spring:message code="label.register"/></a></p>

                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>



