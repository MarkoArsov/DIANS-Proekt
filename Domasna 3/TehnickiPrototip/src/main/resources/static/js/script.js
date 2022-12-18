$(document).ready(function () {

    var map = L.map('main-map').setView([41.9981, 21.4254], 14);

    map.addControl(new L.Control.Fullscreen());

    L.control.locate().addTo(map);

    L.tileLayer('https://tile.openstreetmap.org/{z}/{x}/{y}.png', {
        maxZoom: 19,
        attribution: '&copy; <a href="http://www.openstreetmap.org/copyright">OpenStreetMap</a>'
    }).addTo(map);


    fetch('http://localhost:8085/api/events').then(r => r.json()).then(data => {

        var mainEvents = document.querySelector("#main-events")

        for (let i = 0; i < data.length; i++) {

            var eventId = data[i].id;
            var eventName = data[i].name;
            var eventImg = data[i].imgPath;

            var event = document.createElement("div")
            event.setAttribute("class", "event")
            event.setAttribute("id", eventId)
            mainEvents.append(event)

            ReactDOM.render(<Event
                event={{name: eventName, img: eventImg, id : eventId}}
            />, event)

            var eventLat = data[i].lat
            var eventLng = data[i].lng
            var eventMarker = L.marker([eventLat, eventLng]).addTo(map)

            var eventMarkerContent = document.createElement("div")
            eventMarkerContent.setAttribute("id", eventId)
            eventMarker.bindPopup(eventMarkerContent)

            ReactDOM.render(<MarkerEvent
                event={{name: eventName, img: eventImg}}
            />, eventMarkerContent)

        }

    })


    $('form').each(function () {
        $('input').keypress(function (e) {
            // Enter pressed?
            if (e.which == 10 || e.which == 13) {
                this.form.submit();
            }
        });

        $('input[type=submit]').hide();
    })

})