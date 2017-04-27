var express = require('express');
var app = express();
var basicAuth = require('basic-auth');

var auth = function (req, res, next) {
  function unauthorized(res) {
    res.set('WWW-Authenticate', 'Basic realm=Authorization Required');
    return res.sendStatus(401);
  };

  var user = basicAuth(req);

  if (!user || !user.name || !user.pass) {
    return unauthorized(res);
  };

  if (user.name === 'heiderlopes' && user.pass === 'android2') {
    return next();
  } else {
      console.log('Nao autorizado');
    return unauthorized(res);
  };
};

app.get('/', function(req, res) {
    res.send('Benvindo ao mundo das APIs');
});

app.patch('/led/:ledId/ligar', auth, function(req, res) {
    //res.send('LED: ' + req.params.ledId +' ligado ');
    res.sendStatus(200);
});

app.patch('/led/:ledId/desligar', function(req, res) {
    //res.send('LED: ' + req.params.ledId +' desligado ');
    res.sendStatus(200);
});

var server = app.listen(3000);
console.log('Servidor Express iniciado na porta %s', server.address().port);