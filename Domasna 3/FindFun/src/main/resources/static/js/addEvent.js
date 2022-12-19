$(document).ready(function () {

    console.log("START")

    var mainMarker = null

    var map = L.map('add-event-map').setView([41.9981, 21.4254], 14);

    map.addControl(new L.Control.Fullscreen());

    L.control.locate().addTo(map);

    L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
        maxZoom: 19,
        attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
    }).addTo(map);

    function onMapClick(e) {
        console.log(document.querySelector("#lat").value)
        if (mainMarker !== null){
            map.removeLayer(mainMarker)
        }
        mainMarker = L.marker(e.latlng).addTo(map)
        var lat = e.latlng.lat
        var lng = e.latlng.lng
        document.querySelector("#lat").value = lat
        document.querySelector("#lng").value = lng
    }

    map.on('click', onMapClick);


    $('form').each(function () {
        $('input').keypress(function (e) {
            // Enter pressed?
            if (e.which == 10 || e.which == 13) {
                this.form.submit();
            }
        });

        $('input[type=submit]').hide();
    })

    console.log("END")
})