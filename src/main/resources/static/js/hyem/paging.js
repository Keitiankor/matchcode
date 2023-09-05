function loadPage(pageNumber) {
    $.ajax({
        url: "/your-endpoint-for-pagination?page=" + pageNumber,
        method: "GET",
        success: function(data) {
            $("#pageContent").html(data.content);
            $("#paginationControls").html(data.pagination);
        },
        error: function() {
            console.error("Failed to load page content");
        }
    });
}