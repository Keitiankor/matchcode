$(document).ready(function () {
    $("#matchHistoryButton").click(function () {
        $.ajax({
            url: "matchhistory", // 매치 히스토리를 가져오는 URL
            dataType: "html",
            success: function (data) {
                $("#contents").html(data);
            },
            error: function () {

                console.error("Error loading match history.");
            }
        });
    }); // 매치 히스토리 버튼 클릭시

    $("#personalButton").click(function () {
        $.ajax({
            url: "personal", // 매치 히스토리를 가져오는 URL
            dataType: "html",
            success: function (data) {
                $("#contents").html(data);
            },
            error: function () {

                console.error("Error loading match history.");
            }
        });
    }); // 개인정보 버튼 클릭시

    $("#myPostButton").click(function () {
        $.ajax({
            url: "mypost", // 매치 히스토리를 가져오는 URL
            dataType: "html",
            success: function (data) {
                $("#contents").html(data);
            },
            error: function () {

                console.error("Error loading match history.");
            }
        });
    }); // 내 게시물 버튼 클릭시

    $("#update").click(function () {
        $.ajax({
            url: "personalupdate", // 매치 히스토리를 가져오는 URL
            dataType: "html",
            success: function (data) {
                $("#contents").html(data);
            },
            error: function () {

                console.error("Error loading match history.");
            }
        });
    }); //개인정보 탭에서 '수정하기' 버튼 누를 시


});