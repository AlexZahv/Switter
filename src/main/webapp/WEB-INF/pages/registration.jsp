<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"  %>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <title>Регистрация</title>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css"/>
    <link type="text/css" rel="stylesheet" href="${pageContext.request.contextPath}/resources/css/styles.css"/>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/switter.js"/>
<script>
    function onClickAction(event) {
        checkIsValid(document.getElementById("signupName"));
        checkIsValid(document.getElementById("signupPassword"));
        checkIsValid(document.getElementById("signupEmail"));
        if (document.getElementById("signupEmailagain").value != document.getElementById("signupEmail").value) {
            alert("Электронные почты не совпадают!")
            return false;
        }
    }
    function checkIsValid(element) {
        if (element.value == "") {
            alert("Ошибка заполнения");
            return;
        }
    }
</script>
</head>
<body>

<div class="container" style="margin-top: -300px">
    <div class="row">
        <div class="panel panel-primary" style="border: solid #000000 2px;">
            <div class="panel-body">
                <form:form method="POST" action="registration" role="form" commandName="account" id="registrationForm" accept-charset="windows-1251">
                    <div id="first_step">
                        <div class="form-group">
                            <h2>Create account</h2>
                        </div>
                        <div class="form-group">
                            <label class="control-label" for="signupName"><spring:message code="label.login"/></label>
                            <input id="signupName" type="text" maxlength="50" class="form-control" name="login">
                            <form:errors cssClass="error" path="login" cssStyle="color: red;text-align: center"/>
                        </div>
                        <div class="form-group">
                            <label class="control-label" for="signupPassword"><spring:message code="label.password"/> </label>
                            <input id="signupPassword" type="password" maxlength="25" class="form-control"
                                   placeholder="at least 6 characters" length="40" name="password">
                            <form:errors cssClass="error" path="password" cssStyle="color: red;text-align: center"/>
                        </div>
                        <div class="form-group">
                            <label class="control-label" for="signupEmail">Email</label>
                            <input id="signupEmail" type="email" maxlength="50" class="form-control"
                                   name="emailAddress">
                        </div>
                        <div class="form-group">
                            <label class="control-label" for="signupEmailagain"><spring:message code="label.email_again"/> </label>
                            <input id="signupEmailagain" type="email" maxlength="50" class="form-control">
                        </div>
                        <div class="form-group">
                            <button id="next_step_button" type="button" class="btn btn-info" style="float: right"
                                    onclick="nextStep(this)"><spring:message code="input.next"/>
                            </button>
                        </div>
                    </div>
                    <div class="clear" style="height: 10px"></div>
                    <div id="second_step" style="display: none">
                        <div class="form-group">
                            <label class="control-label" for="name"><spring:message code="label.name"/> </label>
                            <input id="name" type="text" maxlength="50" class="form-control" name="name">
                        </div>
                        <div class="form-group">
                            <label class="control-label" for="surname"><spring:message code="label.surname"/></label>
                            <input id="surname" type="text" maxlength="50" class="form-control" name="surname">
                        </div>
                        <div class="form-group">
                            <label class="control-label" for="country"><spring:message code="label.country"/></label>
                            <input id="country" type="text" maxlength="50" class="form-control" name="country">
                        </div>
                        <div class="form-group">
                            <label class="control-label" for="city"><spring:message code="label.city"/> </label>
                            <input id="city" type="text" maxlength="50" class="form-control" name="city">
                        </div>
                        <div class="form-group">
                            <label class="control-label" for="about"><spring:message code="label.about"/> </label>
                            <textarea id="about" class="form-control" name="shortDescription"
                                      style="height: 120px;resize: none"></textarea>
                        </div>
                        <hr>
                        <div class="form-group">
                            <button id="previous_step_button" type="button" class="btn btn-info" style="float: left"
                                    onclick="nextStep(this)"><spring:message code="label.back"/>
                            </button>
                            <button id="signupSubmit" type="submit" class="btn btn-info" style="float: right"
                                    onclick="return onClickAction(event)"><spring:message code="button.create"/>
                            </button>
                        </div>
                    </div>
                </form:form>
            </div>
        </div>
    </div>
</div>

</body>
</html>


