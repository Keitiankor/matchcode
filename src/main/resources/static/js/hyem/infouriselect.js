$(".recruitview").on("click", function() {
    var recruitId = $(this).data("recruit-id");
    var teamUri = $(this).data("team-uri");
    var teamId = $(this).data("team-id");

    var url;
    if (recruitId == null) {
        url = "/team/view/" + teamUri + "/" + teamId;
    } else {
        url = "/recruit/view/" + recruitId;
    }

    $.ajax({
        url: url,
        type: "GET",
        dataType: "html",
        success: function(data) {
            console.dir(data);
            window.location.href = url;
        },
        error: function(xhr, status, error) {
            console.error("AJAX Error:");
            console.error("Status: " + status);
            console.error("Error message: " + error);
            console.error("Response Text: " + xhr.responseText);
            console.error("Response Object:", xhr); // Log the entire response object
        }

    });
});