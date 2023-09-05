$(function() {
    // 변수를 사용하여 현재 활성화된 버튼을 추적합니다.
    var activeButton = null;

    $("#basketball").click(function() {
        loadSportsData(1); // 농구에 해당하는 sportsId
        // 현재 활성화된 버튼의 클래스 제거
        if (activeButton) {
            activeButton.removeClass("active");
        }
        // 클릭한 버튼에 클래스 추가
        $(this).addClass("active");
        // 클릭한 버튼을 활성화된 버튼으로 설정
        activeButton = $(this);
    });

    $("#futsal").click(function() {
        loadSportsData(2); // 풋살에 해당하는 sportsId
        if (activeButton) {
            activeButton.removeClass("active");
        }
        $(this).addClass("active");
        activeButton = $(this);
    });

    $("#badminton").click(function() {
        loadSportsData(3); // 배드민턴에 해당하는 sportsId
        if (activeButton) {
            activeButton.removeClass("active");
        }
        $(this).addClass("active");
        activeButton = $(this);
    });

    // 나머지 코드는 그대로 유지됩니다.
    function loadSportsData(sportsId) {
        $.ajax({
            url: "/loadsportsdata",
            method: "GET",
            data: { sportsId: sportsId },
            success: function(data) {
                $("#sportstype").html(data);
            }
        });
    }

    var token = $("meta[name='_csrf']").attr("content");
    var header = $("meta[name='_csrf_header']").attr("content");
});