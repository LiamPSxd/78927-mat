var express = require('express');
var router = express.Router();
const client = require('../Client.js');

router.post('/pagos', function(req, res){
    const dataPago = {
        folio : req.body.folio,
        titular: req.body.titular,
        concepto: req.body.concepto,
        cantidad: req.body.cantidad,
        estado: req.body.estado
    }
    console.log(dataPago);
    client.addPago(dataPago, function(error, response){
        console.log(response)
        res.status(200).json({message: response.message})

    });
});

router.get('/pagos', function(req, res){
    const rows = [];
    const call = client.getPagos();
    call.on('data', (data)=>{
        rows.push(data);
    })
    call.on('end', function(){
        res.status(200).json({data: rows});
    })
    call.on('error', function(e){
        console.log('Error al obtener la data', e);
    })
})

module.exports = router;