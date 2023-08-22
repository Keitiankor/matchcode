$(document).ready(function () {
    $("#futsal").click(function () {
        loadHistoryData(1); // 풋살에 해당하는 sportsId
    });

    $("#basketball").click(function () {
        loadHistoryData(2); // 농구에 해당하는 sportsId
    });

    $("#badminton").click(function () {
        loadHistoryData(3); // 배드민턴에 해당하는 sportsId
    });

    function loadHistoryData(sportsId) {
        $.ajax({
            url: "/loadHistoryData", // 요청을 처리할 컨트롤러의 URL
            method: "GET",
            data: { sportsId: sportsId },
            success: function (data) {
                $("#history").html(data); // 받아온 데이터를 history div에 출력
            }
        });
    }
});