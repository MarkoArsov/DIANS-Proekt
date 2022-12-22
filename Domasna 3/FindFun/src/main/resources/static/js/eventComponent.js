function Event({event}) {

    var eventlink = "/events/" + event.id;

    return (

        <div className="card horizontal">
            <div className="card-image">
                <img src={event.img}/>
            </div>
            <div className="card-stacked">
                <div className="card-content">
                    <p>{event.name}</p>
                </div>
                <div className="card-action">
                    <a href={eventlink}> Go to Event</a>
                </div>
            </div>
        </div>

    );
}

function MarkerEvent({event}) {
    return (
        <div className="card markerCard">
            <div className="card-image">
                <img src={event.img} alt={event.img}/>

            </div>
            <div className="card-content">
                <span className="markerCardTitle">{event.name}</span>
            </div>
        </div>
    )
}

