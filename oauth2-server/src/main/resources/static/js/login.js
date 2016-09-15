var LOGIN = {
    dologin:function() {
        var username = $('#username').val();
        var password = $('#password').val();
        if ($.trim(username) == '') {
            alert("用户名不能为空！");
            return false;
        }
        if ($.trim(password) == '') {
            alert("密码不能为空！");
            return false;
        }
        $('#login_form').submit();
    }
};