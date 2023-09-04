$(document).ready(function() {
    var isLoading = false;
    var pageNum = 1;

    function loadMatches(region, sports) {
        if (isLoading) return;

        isLoading = true;
        $("#loading").show();

        // "전체" 선택 시 0으로 변경
        if (region === "전체") {
            region = "";
        }
        if (sports === "전체") {
            sports = "";
        }

        // 서버에 전달되는 값 수정
        if (region === "서울") {
            region = "1";
        } else if (region === "경기") {
            region = "2";
        } else if (region === "인천") {
            region = "3";
        }

        if (sports === "풋살") {
            sports = "1";
        } else if (sports === "농구") {
            sports = "2";
        } else if (sports === "배드민턴") {
            sports = "3";
        }

        $.ajax({
            url: "/match/getmatchesbyregionandsports",
            data: { page: pageNum, region: region, sports: sports },
            method: "GET",
            success: function(matches) {
                if (matches.length === 0) {
                    $("#loading").text("No more matches");
                } else {
                    var tableHtml = "";
                    for (var i = 0; i < matches.length; i++) {
                        tableHtml += "<tr>";
                        tableHtml += "<td>" + matches[i].id + "</td>";
                        tableHtml += "<td><a href=\"/match/post/" + matches[i].id + "\">" + matches[i].mapId + "</a></td>";
                        tableHtml += "<td>" + matches[i].sportsId + "</td>";
                        tableHtml += "<td>" + matches[i].createdDate + "</td>";
                        tableHtml += "</tr>";
                    }
                    $("#matchesTable").append(tableHtml);
                    pageNum++;
                }

                isLoading = false;
                $("#loading").hide();
            }
        });
    }

    // 드롭다운 값이 변경될 때의 이벤트 핸들러 등록
    $("#regionSelect, #sportsSelect").change(function() {
        pageNum = 1;
        $("#matchesTable").empty();
        var region = $("#regionSelect").val();
        var sports = $("#sportsSelect").val();

        loadMatches(region, sports);
    });

    // 초기 데이터 로드
    loadMatches($("#regionSelect").val(), $("#sportsSelect").val());

    // 스크롤 이벤트 감지
    $(window).scroll(function() {
        if ($(window).scrollTop() + $(window).height() >= $(document).height() - 100) {
            loadMatches($("#regionSelect").val(), $("#sportsSelect").val());
        }
    });
});
