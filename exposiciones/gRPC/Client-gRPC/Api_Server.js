const express = require('express');
const app = express();
var morgan = require('morgan');
var cors = require('cors');

//settings
const port = 3000;

//Middlewares
app.use(express.json());
app.use(morgan('dev'));
app.use(cors());

app.use('/express',require('./routes/pago.js'));

app.listen(port,()=>{
    console.log("Servidor en el puerto", port)
})