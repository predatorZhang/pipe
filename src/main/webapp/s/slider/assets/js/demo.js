$(function () {

    // Vertical slider
    $("#v-slider").slider({
        orientation: "horizontal",
        range: "min",
        min: 0,
        max: 100,
        value: 60,
        slide: function (event, ui) {
            $("#amount").val(ui.value);
        }
    });

});