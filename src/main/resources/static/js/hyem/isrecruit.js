$(document).ready(function() {
    $("#isrecruit").click(function() {
        $.ajax({
            url: "/recruit/list",
            dataType: "html",
            success: function(data) {
                $("#main").html(data);
            },
            error: function() {

                console.error("Error loading recruit team list.");
            }
        });
    });

    $("#isnotrecruit").click(function() {
        $.ajax({
            url: "/team/list",
            dataType: "html",
            success: function(data) {
                $("#main").html(data);
            },
            error: function() {
                console.error("Error loading not recruit team list.");
            }
        });
    });

    $("#isnotrecruit").trigger("click");
});