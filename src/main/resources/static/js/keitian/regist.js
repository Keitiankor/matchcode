$(function () {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");

    $("#id-dupchek").on("click", function () {
        $.ajax({
            method: "POST",
            url: "register/accountduplicationcheck",
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
            },
            data: {
                account: $("#id-account").val(),
            },
            success: function (data) {
                if (data) {
                    alert("사용 가능한 아이디 입니다.");
                    $("#id-isAccountNotDup").prop("checked", "checked");
                    $("#id-account").attr("disabled","disabled");
                } else {
                    alert("중복된 아이디 입니다.");
                }
            },
        });
    });

    $("#id-verifymail").on("click", function () {
        alert("메일을 발송하였습니다.\n 잠시만 기다려주세요");
        $("#id-verifymail").attr("disabled","disabled");
        $.ajax({
            method: "POST",
            url: "register/emailverifying",
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
            },
            data: {
                mailAddress: $("#id-mailAddress").val(),
            },
            success: function () {
                $("#id-verifyingcheck").removeAttr("disabled");
            },
            error: function(){
                $("#id-verifymail").removeAttr("disabled");
            }
        });
    });

    $("#id-verifyingcheck").on("click", function () {
        $.ajax({
            method: "POST",
            url: "register/verifyingcheck",
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
            },
            data: {
                inputString: $("#id-verifycode").val(),
            },
            success: function (data) {
                if (data === true) {
                    alert("확인되었습니다.");
                    $("#id-verifyingcheck").attr("disabled", "disabled");
                    $("#id-isverifyied").prop("checked", "checked");
                    $("#id-verifycode").attr("disabled", "disabled");
                    $("#id-mailAddress").attr("disabled", "disabled");
                } else {
                    alert("인증번호가 일치하지 않습니다\n다시 확인 부탁드립니다");
                }
            },
        });
    });

    $("#id-resister").on("click", function(){
        $.ajax({
            method: "POST",
            url: "/register",
            beforeSend: function(xhr){
                xhr.setRequestHeader(header, token);
            },
            data: {
                account: $("#id-account").val(),
                password: $("#id-password").val(),
                name: $("#id-name").val(),
                phone: $("#id-phone").val(),
                birthday: $("#id-birthday").val(),
                mailAddress: $("#id-mailAddress").val(),
                isAccountNotDup: $("#id-isAccountNotDup").is(":checked"),
                isVerifyied: $("#id-isverifyied").is(":checked"),
            },
            success: function(data){
                if(data === "redirect:"){
                    location.href = location.host;
                }
            }
        })
    })
});
