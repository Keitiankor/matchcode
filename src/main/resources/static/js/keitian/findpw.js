$(function() {
    let token = $("meta[name='_csrf']").attr("content");
    let header = $("meta[name='_csrf_header']").attr("content");

    $("#ok").on("click", function() {
        $.ajax({
            method: "POST",
            url: "findpassword/requestpassword",
            beforeSend: function(xhr) {
                xhr.setRequestHeader(header, token);
            },
            data: {
                account: $("#account").val(),
                mailAddress: $("#mailAddress").val()
            },
            success: function(data) {
                const receive = JSON.parse(data);
                if (receive.noAccount !== receive.tempPassword && receive.noMatchEmail !== receive.tempPassword) {
                    alert("메일이 전송되었습니다.");
                    location.replace("/login");
                } else {
                    alert(receive.tempPassword);
                    location.reload();
                }
            }
        });
    });
});