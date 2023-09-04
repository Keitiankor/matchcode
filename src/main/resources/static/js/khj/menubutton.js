$(document).ready(function() {
    // 변수를 사용하여 현재 활성화된 버튼을 추적합니다.
    var activeButton = null;

    $("#matchHistoryButton").click(function() {
        loadContent("matchhistory", $(this));
    });

    $("#personalButton").click(function() {
        loadContent("personal", $(this));
    });

    $("#myPostButton").click(function() {
        loadContent("mypost", $(this));
    });

    $("#update").click(function() {
        loadContent("personalupdate", $(this));
    });

    // 함수로 AJAX 호출과 버튼 스타일 관리를 분리합니다.
    function loadContent(url, button) {
        $.ajax({
            url: url,
            dataType: "html",
            success: function(data) {
                $("#contents").html(data);
                // 현재 활성화된 버튼의 클래스 제거
                if (activeButton) {
                    activeButton.removeClass("active");
                }
                // 클릭한 버튼에 클래스 추가
                button.addClass("active");
                // 클릭한 버튼을 활성화된 버튼으로 설정
                activeButton = button;
            },
            error: function() {
                console.error("Error loading content.");
            }
        });
    }
});