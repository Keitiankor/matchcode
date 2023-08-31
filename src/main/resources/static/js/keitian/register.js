$(function () {
    let token = $("meta[name='_csrf']").attr("content");
    let header = $("meta[name='_csrf_header']").attr("content");

    $("#dupcheck").on("click", function () {
        $.ajax({
            method: "POST",
            url: "register/accountduplicationcheck",
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
            },
            data: {
                account: $("input[name='account']").val(),
            },
            success: function (data) {
                if (data) {
                    alert("사용 가능한 아이디 입니다.");
                    $("input[name='isAccountNotDup']").attr("checked", "checked");
                    $("input[name='account']").attr("disabled","disabled");
                } else {
                    alert("중복된 아이디 입니다.");
                }
            },
        });
    });

    $("#verify-mail").on("click", function () {
        alert("메일을 발송하였습니다.\n 잠시만 기다려주세요");
        $("#verify-mail").attr("disabled","disabled");
        $.ajax({
            method: "POST",
            url: "register/emailverifying",
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
            },
            data: {
                mailAddress: $("input[name='mailAddress']").val(),
            },
            success: function () {
                $("#verifyingcheck").removeAttr("disabled");
            },
            error: function(){
                $("input[name='verifymail']").removeAttr("disabled");
            }
        });
    });

    $("#verifyingcheck").on("click", function () {
        $.ajax({
            method: "POST",
            url: "register/verifyingcheck",
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
            },
            data: {
                inputString: $("input[name='verifycode']").val(),
            },
            success: function (data) {
                if (data === true) {
                    alert("확인되었습니다.");
                    $("#verifyingcheck").attr("disabled", "disabled");
                    $("input[name='isVerifyied']").attr("checked", "checked");
                    $("input[name='verifycode']").attr("disabled", "disabled");
                    $("input[name='mailAddress']").attr("disabled", "disabled");
                } else {
                    alert("인증번호가 일치하지 않습니다\n다시 확인 부탁드립니다");
                }
            },
        });
    });
});
