$(function() {
    $("#futsal").click(function() {
        loadSportsData(1); // 풋살에 해당하는 sportsId
    });

    $("#basketball").click(function() {
        loadSportsData(2); // 농구에 해당하는 sportsId
    });

    $("#badminton").click(function() {
        loadSportsData(3); // 배드민턴에 해당하는 sportsId
    });

    function loadSportsData(sportsId) {
        $.ajax({
            url: "/loadsportsdata", // 요청을 처리할 컨트롤러의 URL
            method: "GET",
            data: { sportsId: sportsId },
            success: function(data) {
                $("#sportstype").html(data); // 받아온 데이터를 history div에 출력
            }
        });
    }

    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");

});
