function eventToString(event) {
    // return some string representation of this event
    return event.evtGroup + " | " + event.moduleName + " | " + event.subSystem + " | " + event.type + " | " + event.millis;
}

window.__gwtStatsEvent = function (event) {
    var loggingDiv = document.getElementById('log');
    if (!loggingDiv) {
        // Our logging div is not yet attached to the DOM
        // Initialize a temporary buffer if needed
        this.buffer = (this.buffer) ? this.buffer : [];
        // log data here
        this.buffer.push(event);
    } else {
        if (this.buffer) {
            // We have some data that was reported before the div was connected
            for (var i = 0; i < buffer.length; i++) {
                // print it all to the div
                var bufferedEvent = buffer[i];
                var logline = document.createElement("div");
                logline.id = "logline";
                logline.innerHTML = eventToString(bufferedEvent);
                loggingDiv.appendChild(logline);
            }
            this.buffer = null;
        }
        // log the current event to the div
        var logline = document.createElement("div");
        logline.id = "logline";
        logline.innerHTML = eventToString(event);
        loggingDiv.appendChild(logline);
    }
    // The collector function should indicate success
    return true;
}

