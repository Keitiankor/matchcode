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

    $("#myPositionButton").click(function () {
        $.ajax({
            url: "myposition", // 매치 히스토리를 가져오는 URL
            dataType: "html",
            success: function (data) {
                $("#contents").html(data);
            },
            error: function () {

                console.error("Error loading match history.");
            }
        });
    }); // 포지션 버튼 클릭시

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
    }); // 매치 히스토리 버튼 클릭시

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
    }); // 매치 히스토리 버튼 클릭시

    $("#recordButton").click(function () {
        $.ajax({
            url: "record", // 매치 히스토리를 가져오는 URL
            dataType: "html",
            success: function (data) {
                $("#contents").html(data);
            },
            error: function () {

                console.error("Error loading match history.");
            }
        });
    }); // 매치 히스토리 버튼 클릭시


});