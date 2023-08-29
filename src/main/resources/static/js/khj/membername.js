$(".show-names-button").click(function () {
    var matchId = $(this).data("matchid");
    var container = $("#" + matchId);

    if (container.children().length === 0) {
        $.ajax({
            url: "/loadmatchdata", // 컨트롤러에서 해당 URL 매핑 필요
            data: { matchId: matchId },
            method: "GET",
            success: function (data) {
                let list = Object.values(data);
                for (let i = 0; i < list.length; i++) {
                    container.append($("<td>").append(list[i]));
                }
                container.show(); // 테이블 보이도록 표시
            },
        });
    } else {
        container.toggle(); // 테이블 토글
    }
});