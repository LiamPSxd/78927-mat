const mysql = require('mysql');
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

var PROTO_PATH = '../Proto/demo.proto';

var grpc = require('@grpc/grpc-js');
var protoLoader = require('@grpc/proto-loader');
var packageDefinition = protoLoader.loadSync(
    PROTO_PATH,
    {keepCase: true,
     longs: String,
     enums: String,
     defaults: true,
     oneofs: true
    });
var demoProto = grpc.loadPackageDefinition(packageDefinition).demo;

function addPago(call, callback) {
  const query = `INSERT INTO pagos (folio, titular, concepto, cantidad, estado) VALUES (${call.request.folio},"${call.request.titular}","${call.request.concepto}",${call.request.cantidad},${call.request.estado});`
  connection.query(query, function(err, rows, fields){
    if(err) throw err;
    callback(null, {message: 'success'})
  })
}

function getPagos(call){
  const query = 'select * from pagos;';
  connection.query(query, function(err, rows, fields){
    if(err) throw err;
    for(const data of rows){
      console.log("data",data);
      call.write(data);
    }
    call.end();
  });
}

function main() {
  var server = new grpc.Server();
  server.addService(demoProto.Pagos.service, {
    //Procedimiento remotos
    addPago:addPago,
    getPagos:getPagos
  });
  server.bindAsync('0.0.0.0:50051', grpc.ServerCredentials.createInsecure(), () => {
    server.start();
    console.log('gRPC server on port 50051')
  });
}

main();
