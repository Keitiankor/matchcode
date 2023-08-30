$(document).ready(function() {
    function getMatchesByRegion(region) {
        $.ajax({
            url: "/match/getmatchesbyregion",
            data: { region: region },
            method: "GET",
            dataType: "json", // 데이터를 JSON 형식으로 받아옴
            success: function(matches) {
                console.log(typeof matches); // 데이터를 콘솔에 출력

                // 받아온 데이터를 화면에 렌더링
                var resultHtml = '';
                for (var i = 0; i < matches.length; i++) {
                    resultHtml += '<tr>';
                    resultHtml += '<td>' + matches[i].id + '</td>';
                    resultHtml += '<td><a href="/match/post/' + matches[i].id + '">' + matches[i].mapId + '</a></td>';
                    resultHtml += '<td>' + matches[i].sportsId + '</td>';
                    resultHtml += '<td>' + matches[i].createdDate + '</td>';
                    resultHtml += '</tr>';
                }
                $("#result tbody").html(resultHtml);
            },
        });
    }

    $("#seoulButton").click(function() {
        getMatchesByRegion(1);
    });

    $("#gyeonggiButton").click(function() {
        getMatchesByRegion(2);
    });

    $("#incheonButton").click(function() {
        getMatchesByRegion(3);
    });
});
