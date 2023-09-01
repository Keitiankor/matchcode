$(function() {
    let token = $("meta[name='_csrf']").attr("content");
    let header = $("meta[name='_csrf_header']").attr("content");

    $("#ok").on("click", function() {
        $.ajax({
            method: "POST",
            url: "changepassword/requestchange",
            beforeSend: function(xhr) {
                xhr.setRequestHeader(header, token);
            },
            data: {
                oldPassword: $("#password-old").val(),
                newPassword: $("#password-new").val()
            },
            success: function(data) {
                if (data) {
                    alert("변경되었습니다.");
                    location.replace("/login");
                } else {
                    alert("비밀번호가 일치하지 않습니다.");
                    location.reload();
                }
            }
        });
    });
});