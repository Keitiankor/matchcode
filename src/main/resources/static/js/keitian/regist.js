$(function () {
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
    $("#sendverifymail").on("click", function () {
        $.ajax({
            method: "POST",
            url: "regist/emailverifying",
            beforeSend: function (xhr) {
                xhr.setRequestHeader(header, token);
            },
            data: {
                mailAdress: $("#mailAddress").val(),
            },
            success: function (data) {
                alert(data);
            },
        });
    });
});
