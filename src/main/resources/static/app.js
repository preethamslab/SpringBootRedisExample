var ws;
function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    console.log("Connection Triggered");
	ws = new WebSocket('ws://localhost:7500/socket');
	console.log(ws);
	ws.onmessage = function(data){
		showGreeting(data.data);
	}
	setConnected(true);
	//ws.onopen = () => ws.send("data2");
}

function disconnect() {
    if (ws != null) {
        ws.close();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
	var data = $("#name").val();
	if(ws.readyState === 1){
	    ws.send(data);
	} else {
	    console.log("Websocket not in ready state : " + ws.readyState);
	}
}

function showGreeting(message) {
    $("#greetings").append("<tr><td> " + message + "</td></tr>");
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
});