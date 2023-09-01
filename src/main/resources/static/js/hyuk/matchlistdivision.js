$(document).ready(function() {
    function getMatchesByRegionAndSports(region, sports) {
        $.ajax({
            url: "/match/getmatchesbyregionandsports",
            data: { page: 1, region: region, sports: sports }, // "page" 값을 추가하여 요청
            method: "GET",
            success: function(matches) {
                console.log(typeof matches);
                $("#result").html(matches);
            },
        });
    }

    $("#seoulButton").click(function() {
        getMatchesByRegionAndSports(1, -1); // 0은 모든 종목을 의미
    });

    $("#gyeonggiButton").click(function() {
        getMatchesByRegionAndSports(2, -1);
    });

    $("#incheonButton").click(function() {
        getMatchesByRegionAndSports(3, -1);
    });

    $("#footballButton").click(function() {
        getMatchesByRegionAndSports(1, 1); // 1은 풋살을 의미
    });

    $("#basketballButton").click(function() {
        getMatchesByRegionAndSports(2, 2); // 2는 농구를 의미
    });

    $("#badmintonButton").click(function() {
        getMatchesByRegionAndSports(3, 3); // 3은 배드민턴을 의미
    });
});
