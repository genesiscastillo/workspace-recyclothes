var stompClient = null;

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
    var socket = new SockJS('/recyclothesws');
	console.log(socket);
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', function (greeting) {
            showGreeting(JSON.parse(greeting.body).content);
        });
        stompClient.subscribe('/user/topic/catalogo', function ( catalogo ) {
            console.log(JSON.parse(catalogo.body));
			mostrarCatalogo( JSON.parse(catalogo.body) );
        });
        stompClient.subscribe('/topic/producto', function ( producto ) {
            console.log('producto -> ' , JSON.parse(producto.body));
        });
    });
}


function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function sendName() {
    stompClient.send("/app/hello", {}, JSON.stringify({'name': $("#name").val()}));
}

function sendSolicitarCatalogo(){
	console.log('sendSolicitarCatalogo');
	stompClient.send("/app/solicitarCatalogo", {} , JSON.stringify({'genero': 'NINO','categoria':'PACK','talla':'TALLA_1','estado':'DISPONIBLE'}));
}
function sendReservarProducto(){
	console.log('sendReservarProducto');
	var producto ={
		    "id": 1,
    		"nombre": "PEPITO BABY",
    		"genero": "NINO",
    		"talla": "TALLA_1",
    		"categoria": "PACK",
    		"estado": "DISPONIBLE",
	}
	stompClient.send("/app/reservarProducto", {} , JSON.stringify(producto));
}

function showGreeting(message) {
    $("#greetings").append("<tr><td>" + message + "</td></tr>");
}

function showCatalogo(catalogo) {
    //$("#greetings").append("<tr><td>" + message + "</td></tr>");
}

function mostrarCatalogo( catalogo  ){
	$.each( catalogo , function(index , producto){
		$('#tablaCatalogo').append('<tr><td>'+producto.nombre +'</td></tr>');
	});

}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { sendName(); });
	$( "#sendSolicitarCatalogo" ).click(function() { sendSolicitarCatalogo(); });
	$( "#sendReservarProducto" ).click(function() { sendReservarProducto(); });
	
});

