<%@page contentType="text/html;charset=UTF-8" %>

<%@include file="/taglibs.jsp" %>

<!DOCTYPE html>

<html>

<head lang="en">

    <meta charset="UTF-8">

    <title></title>

    <meta http-equiv="Content-Type" content="text/html;charset=utf-8"/>

    <meta http-equiv="Cache-Control" content="no-store"/>

    <meta http-equiv="Pragma" content="no-cache"/>

    <meta http-equiv="Expires" content="0"/>

    <link type="text/css" rel="stylesheet" href="${ctx}/s/login/LoginCss.css" media="screen"/>

    <script type="text/javascript" src="${ctx}/s/jquery-easyui-1.4.3/jquery.min.js"></script>
    <script type="text/javascript" src="${ctx}/s/media/js/jquery.cookie.min.js"></script>

    <script type="text/javascript">

        if (window != top){

            top.location.href = location.href;

        }

        var submitForm = function () {
            if ($('#rember_me').is(':checked')) {
                var username = $("#username").val();
                var password = $("#password").val();
                $.cookie("alarm_username", username, {expires: 90});
                $.cookie("alarm_password", password, {expires: 90});

            }
            else {
                $.cookie("alarm_username", "", {expires: -1});
                $.cookie("alarm_username", "", {expires: -1});
            }

            $.ajax({

                type: "POST",

                url: "${ctx}/user/login.do",

                data: $('#loginForm').serialize(),

                dataType: "json",

                success: function (data) {

                    var jData = eval(data);

                    if (jData.success == true) {
                        if(null==jData.user.authorities){
                            alert("用户名无该系统权限！");
                            return;
                        }

                        window.location.href = "${ctx}/content/user/department-info-list.jsp";

                    }
                    else {

                        alert(jData.message);

                    }

                },

                error: function (request) {

                    alert("登录失败！");

                }
            });

        };


        $(document).ready(function () {

            var username = $.cookie("alarm_username");
            var password = $.cookie("alarm_password");
            /*
             var fdad =   $('#rember_me').is('checked');
             */
            if (username && password) {
                $("#username").val(username);
                $("#password").val(password);
                $('#rember_me').attr('checked', true);
            } else {
                $('#rember_me').attr('checked', false);
            }

        });


        /*var regUser = function(){

            window.location.href = '${ctx}/reguser.jsp';

        }*/

    </script>

</head>

<body class="Login">

<form id="loginForm" action="" method="post">

    <div class="login_bg">

        <img src="s/login/images/login_bg.jpg"/>

        <div class="login_dw"></div>

    </div>

    <div class="login_fot">

        <img src="s/login/images/login_fotBg.jpg"/>

        <div class="login_BT">

            智慧太湖新城地下管网综合信息平台

        </div>

        <div class="login_div">

            <input type="text" name="username" class="userName" id="username" class="text required"
                   onkeydown="KeyDown()"/>

            <input type="password" name="password" class="password" id="password" class="text required"
                   onkeydown="KeyDown()"/>

            <input type="checkbox" class="rember_me" name="checkbox" id="rember_me"/>

            <div class="txtRember">记住密码</div>

            <input type="button" id="login" class="LoginBtn" name="submit" onclick="submitForm()"/>

            <%--<input type="button" class="resetBtn" name="register" onclick="regUser()"/>--%>

        </div>

    </div>

</form>

</body>

</html>

<script type="text/javascript">

    $(".login_bg").css('height', $('.Login').height() - $('.login_fot').height() + 'px');

    window.onresize = function () {

        $(".login_bg").css('height', $('.Login').height() - $('.login_fot').height() + 'px');

    }

    function KeyDown() {
        if (event.keyCode == 13) {
            event.returnValue = false;
            event.cancel = true;
            $("#login").click();
        }
    }
</script>