function Event({event}) {

    var eventlink = "/events/" + event.id;

    return (
        <div className="card horizontal">
            <div className="card-image">
                <img src={event.img} alt={event.img}/>
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

function EventPageImg({event}){

    var inlineStyle = "background-image:url(" + event.img + "); background-position: center; background-repeat: no-repeat; background-size: cover;"

    return(
        <div className="row">
            <div className="col s12" id="imgDiv" style={inlineStyle}>
            </div>
        </div>
    )
}
