var mysql = require('mysql');
const connection = mysql.createConnection({
    host : 'localhost',
    database : 'pagosdb',
    user : 'root',
    password : '1887',
});

connection.connect(function(err) {
    if (err) {
        console.error('Error de conexion: ' + err.stack);
        return;
    }
    console.log('Conectado con el identificador ' + connection.threadId);
});

exports.module = connection