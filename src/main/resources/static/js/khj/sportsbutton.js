$(function () {
    $("#futsal").click(function () {
        loadSportsData(1); // 풋살에 해당하는 sportsId
    });

    $("#basketball").click(function () {
        loadSportsData(2); // 농구에 해당하는 sportsId
    });

    $("#badminton").click(function () {
        loadSportsData(3); // 배드민턴에 해당하는 sportsId
    });

    function loadSportsData(sportsId) {
        $.ajax({
            url: "/loadsportsdata", // 요청을 처리할 컨트롤러의 URL
            method: "GET",
            data: { sportsId: sportsId },
            success: function (data) {
                $("#sportstype").html(data); // 받아온 데이터를 history div에 출력
            },
        });
    }
    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");

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
});
