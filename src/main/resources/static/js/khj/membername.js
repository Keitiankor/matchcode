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
                    //매너점수 증감은 아직 무리인 것 같다 ㅠㅠ
                    //매너점수 리뷰를 위한 추가 버튼
                    // $("<button>").text("▲").addClass("increase-manner").attr("data-memberid", memberId),
                    // $("<button>").text("▼").addClass("decrease-manner").attr("data-memberid", memberId)
                    // ));
                }
                container.show(); // 테이블 보이도록 표시
            },
        });
    } else {
        container.toggle(); // 테이블 토글
    }
});

// -----------여기서부턴 매너점수 증가,감소 관련

// '+' 버튼 클릭 시 매너점수 증가
// $(document).on("click", ".increase-manner", function () {
//     var memberId = $(this).data("memberid");
//     console.log("Increase manner for member: " + memberId);
//     alert("서버로 보내는 Id는 " + memberId + "입니다.")
//
//     //서버로 매너점수를 증가시키는 명령을 서버로 전송
//     $.ajax({
//         url: "/increaseManner", // 서버에서 처리하는 URL 설정
//         method: "POST",
//         data: { memberId: memberId },
//         success: function (response) {
//             console.log("매너점수 증가 완료!");
//             // 필요한 경우, 매너점수 업데이트 등의 작업 수행
//         },
//         error: function (error) {
//             console.error("Failed to increase manner: " + error);
//         }
//     });
// });

// '-' 버튼 클릭 시 매너점수 감소
// $(document).on("click", ".decrease-manner", function () {
//     var memberId = $(this).data("memberid");
//     console.log("Decrease manner for member: " + memberId);
//     alert("서버로 보내는 Id는 " + memberId + "입니다.")
//
//
//     //AJAX 요청을 통해 매너점수를 감소시키는 서버로 데이터 전송
//     $.ajax({
//         url: "/decreaseManner", // 서버에서 처리하는 URL 설정
//         method: "POST",
//         data: { memberId: memberId },
//         success: function (response) {
//             console.log("매너점수 감소 완료!");
//             // 필요한 경우, 매너점수 업데이트 등의 작업 수행
//         },
//         error: function (error) {
//             console.error("Failed to decrease manner: " + error);
//         }
//     });
// });
