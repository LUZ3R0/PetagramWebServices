var express = require('express');
var app = express();

app.set('port', (process.env.PORT || 5000));

var bodyParser = require("body-parser");
app.use(bodyParser.json()); //soporte codificacion json
app.use(bodyParser.urlencoded({ extended: true })); //soporte para codificacion de las url

var firebase = require("firebase");
firebase.initializeApp({
  serviceAccount: "PetagramBD-145404b873f6.json",
  databaseURL: "https://petagrambd.firebaseio.com"
});


app.use(express.static(__dirname + '/public'));

// views is directory for all template files
app.set('views', __dirname + '/views');
app.set('view engine', 'ejs');

app.get('/android', function(request, response) {
  response.render('pages/index');
});


var uri_servicio = '/registrar-usuario';

app.post('/registrar-usuario', function(request, response) {

   	// Parámetros POST
	var id_dispositivo = request.body.id_dispositivo;
	var id_usuario_instagram = request.body.id_usuario_instagram;
   
   	// Guarda los parametros en la base de datos
	var db = firebase.database();
	var db_Ref = db.ref(uri_servicio).push();
	db_Ref.set({
		id_dispositivo: id_dispositivo,
      	id_usuario_instagram: id_usuario_instagram
	});	

  	// Obtiene el identificador del registro
 	var path = db_Ref.toString(); //https://petagrambd.firebaseio.com/registrar-usuario/-KOR9-Mde5Jal0r7PNdJ
	var pathSplit = path.split(uri_servicio + "/")
	var idAutoGenerado = pathSplit[1];

   	// Regresa la respuesta en formato JSON
	var respuesta = generarRespuestaJSON(db, idAutoGenerado);
	response.setHeader("Content-Type", "application/json");
   	response.send(JSON.stringify(respuesta));

});

function generarRespuestaJSON(db, idAutoGenerado) {
	var respuesta = {};
	var usuario = "";
	var db_Ref = db.ref(uri_servicio);
	db_Ref.on("child_added", function(snapshot, prevChildKey) {
		usuario = snapshot.val();
		respuesta = {
			id_registro: idAutoGenerado,
			id_dispositivo: usuario.id_dispositivo,
			id_usuario_instagram: usuario.id_usuario_instagram
		};
	});
 
	return respuesta;
}

app.listen(app.get('port'), function() {
  console.log('Node app is running on port', app.get('port'));
});


