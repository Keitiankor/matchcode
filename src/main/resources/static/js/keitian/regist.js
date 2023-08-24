$(function () {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");

    $("#iddupchek").on("click", function () {
        $.ajax({
            method: "POST",
            url: "regist/accountduplicationcheck",
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
            },
            data: {
                account: $("#account").val(),
            },
            success: function (data) {
                if (data) {
                    alert("사용 가능한 아이디 입니다.");
                    $("#isAccountNotDup").prop("checked", true);
                    $("#account").attr("disabled", true);
                } else {
                    alert("중복된 아이디 입니다.");
                }
            },
        });
    });

    $("#verifymail").on("click", function () {
        alert("메일을 발송하였습니다.");
        $.ajax({
            method: "POST",
            url: "regist/emailverifying",
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
            },
            data: {
                mailAddress: $("#mailAddress").val(),
            },
            success: function () {
                $("#verifymail").attr("disabled", true);
                $("#verifyingcheck").removeAttr("disabled");
            },
        });
    });

    $("#verifyingcheck").on("click", function () {
        $.ajax({
            method: "POST",
            url: "regist/verifyingcheck",
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
            },
            data: {
                inputString: $("#verifycode").val(),
            },
            success: function (data) {
                if (data == true) {
                    alert("확인되었습니다.");
                    $("#verifyingcheck").attr("disabled", true);
                    $("#isverifyied").prop("checked", true);
                    $("#verifycode").attr("disabled", true);
                    $("#mailAddress").attr("disabled", true);
                } else {
                    alert("인증번호가 일치하지 않습니다\n다시 확인 부탁드립니다");
                }
            },
        });
    });
});
