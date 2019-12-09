var inicioModule = (function () {

    var waiting = false;
    var chips = new Array();
    var bets = new Array();


    var dataToSend = {
        salaNombre: null,
        usuario: null
    };

    var dataToUser = {
        sala: null,
        usuario: null,
        numero: null
    };

    var stompClient = null;


    var postSala = function (salaNombre) {
        var postPromise = $.ajax({
            url: "/Salas",
            type: 'POST',
            data: salaNombre,
            contentType: "application/json"
        });

        postPromise.then(
            function () {
                console.info('Post OK');
            },
            function () {
                console.info('Post NOK');
            }
        );

        return postPromise;
    };

    var updateTableSalas = function (data) {
        $("#salasTable tbody").empty();
        data.map(function (val, index) {
            console.log(val + " " + index);
            var toAdd = '<tr><td>' + val.nombre + '</td><td>' + val.participantes + '</td><td>' + val.betValue + '</td> <td><button type="button" class="btn btn-secondary" ' +
                'onclick="inicioModule.joinSala(this.value,' + val.betValue + ',' + val.participantes + ')" value="' + val.nombre + '">Join </button></td></tr>';
            $("#salasTable tbody").append(toAdd);
        })
    }

    var getSalas = function () {
        var getPromise = $.get("/Salas");

        getPromise.then(
            function (data) {
                updateTableSalas(JSON.parse(data));
            },
            function () {
                console.log('get failed');
            }
        );

        return getPromise;
    };


    var putUsuarioSala = function () {
        var putPromise = $.ajax({
            url: '/Salas/' + dataToSend.salaNombre,
            type: 'PUT',
            data: JSON.stringify(dataToSend),
            contentType: 'application/json'
        });

        putPromise.then(
            function () {
                console.info('PUT OK');
            },
            function () {
                console.info('PUT NOK');
            }
        );

        return putPromise;
    };

    var postApuestaUsuario = function () {
        var postPromise = $.ajax({
            url: "/ruleta/apuestaUser",
            type: 'POST',
            data: JSON.stringify(dataToUser),
            contentType: 'application/json'
        });

        postPromise.then(
            function () {
                console.info('POST OK');
            },
            function () {
                console.info('POST NOK');
            }
        );

        return postPromise;
    };



    return{
        reInt: function (min, max) {
            return Math.floor(Math.random() * (max - min + 1)) + min;
        },
        setWaiting: function (value) {
            waiting = value;
            var ncoins = bets.length;
            for (var i = 0; i < ncoins; i++) {
                document.body.removeChild(chips[bets[i]].pop());
            }
            bets = new Array();
        },
        apostar: function (casilleroVal) {

            //alert(casilleroVal)
            console.log(rouletteModule.apostando());
            if (rouletteModule.apostando()) {


                var userEmail = cookieModule.getCookies("usuario");
                dataToUser.numero = casilleroVal.value;
                dataToUser.sala = document.getElementById("tableNombre").innerHTML;

                //alert(dataToSend.salaNombre)
                if (document.getElementById("saldoHeader").innerHTML - document.getElementById("tableBetValue").innerHTML => 0) {

                    stompClient.send("/app/apostar/" + dataToSend.salaNombre + "/" + userEmail + "/" + casilleroVal.value, {}, null);
                    //postApuestaUsuario();


                    var lugar = document.getElementById(casilleroVal.value);
                    var _x;
                    var _y;
                    if (lugar.innerHTML === '0') {

                        _x = lugar.offsetLeft + 60;
                        _y = lugar.offsetTop + 50;
                    }
                    else {
                        _x = lugar.offsetLeft;
                        _y = lugar.offsetTop;
                    }

                    var img = document.createElement('img');
                    img.src = "../imagen/ficha.png";
                    img.style.zIndex = "0";
                    img.style.position = "absolute";

                    var rX = Math.floor(Math.random() * (10 - (-10) + 1)) + -10;
                    var rY = Math.floor(Math.random() * (10 - (-10) + 1)) + -10;

                    img.style.left = (_x + rX + 250) + "px";
                    img.style.top = (_y + rY + 150) + "px";

                    img.style.width = "20px";
                    img.style.pointerEvents = "none";
                    document.body.appendChild(img);

                    if (chips[casilleroVal.value] == null)
                        chips[casilleroVal.value] = new Array(0);
                    chips[casilleroVal.value].push(img);
                    bets.push(casilleroVal.value);


                }
                else {
                    Swal.fire({
                        icon: 'error',
                        title: 'Oops...',
                        text: 'Saldo insuficiente!',
                    });
                }
            }


        },
        joinSala: function (salaNombre, apuestaSala, participantes) {
            if (participantes+1 <= 4) {
                dataToSend.salaNombre = salaNombre;
                dataToSend.usuario = cookieModule.getCookies("usuario");

                putUsuarioSala().then(function (data) {
                    console.log(data);


                    window.open('/inicio.html');
                    if(data==="ONGOING"){
                        rouletteModule.playing();
                    }
                    document.title = salaNombre;

                    //stompClient.send("/app/joinSala." + dataToSend.salaNombre, {}, JSON.stringify(dataToSend));
                    document.getElementById("main").style.display = '';
                    document.getElementById("mySidenav").style.display = 'none';
                    document.getElementById("Welcome").style.display = 'none';
                    document.getElementById("tableNombre").innerHTML = salaNombre;
                    document.getElementById("tableBetValue").innerHTML = apuestaSala;


                    stompClient.subscribe('/topic/salas.' + salaNombre, function (eventbody) {
                        updateTableSalas(JSON.parse(eventbody.body));
                    });
                    
                    stompClient.subscribe('/topic/endgame/' + salaNombre, function (eventbody) {
                       rouletteModule.endgame();
                    });

                    stompClient.subscribe('/topic/userSaldo/' + dataToSend.usuario, function (eventbody) {
                        document.getElementById("saldoHeader").innerHTML = eventbody.body;
                    });

                    stompClient.subscribe('/topic/heartbeat/' + salaNombre, function (eventbody) {
                        stompClient.send('/app/heartbeat/' + salaNombre + '/' + dataToSend.usuario, {}, "");
                    });

                    stompClient.subscribe('/topic/apuestas/' + salaNombre, function (eventbody) {
                        console.log('El jugador : ' + (JSON.parse(eventbody.body)).player + ' aposta en el casillero : ' + (JSON.parse(eventbody.body)).casillero);
                        var player = (JSON.parse(eventbody.body)).player;
                        var casilleroVal = (JSON.parse(eventbody.body)).casillero;
                        if (player != dataToSend.usuario) {
                            var lugar = document.getElementById(casilleroVal);
                            var _x;
                            var _y;
                            if (lugar.innerHTML === '0') {

                                _x = lugar.offsetLeft + 60;
                                _y = lugar.offsetTop + 50;
                            }
                            else {
                                _x = lugar.offsetLeft;
                                _y = lugar.offsetTop;
                            }
                            var img = document.createElement('img');
                            img.src = "../imagen/fichaAzul.png";
                            img.style.zIndex = "0";
                            img.style.position = "absolute";

                            var rX = Math.floor(Math.random() * (10 - (-10) + 1)) + -10;
                            var rY = Math.floor(Math.random() * (10 - (-10) + 1)) + -10;

                            img.style.left = (_x + rX + 250) + "px";
                            img.style.top = (_y + rY + 150) + "px";

                            img.style.width = "20px";
                            img.style.pointerEvents = "none";
                            document.body.appendChild(img);

                            if (chips[casilleroVal] == null)
                                chips[casilleroVal] = new Array(0);
                            chips[casilleroVal].push(img);
                            bets.push(casilleroVal);
                        }
                    });

                    stompClient.subscribe('/topic/startcountdown.' + dataToSend.salaNombre, function (eventbody) {
                        if (waiting == false) {
                            waiting = true;
                            rouletteModule.countdown(eventbody.body);
                        }
                    });
                });


            }
            else {
                Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: 'Sala llena!',
                });
            }
            /*putUsuarioSala().then(getSalas).then(function () {
             document.getElementById("main").style.display ='';
             document.getElementById("Welcome").style.display ='none';
             document.getElementById("tableNombre").innerHTML = salaNombre;
             });*/
        },
        nuevaSala: function () {
            var salaNombre = document.getElementById("nuevaSalaNombre").value;
            var defaultBet = document.getElementById("nuevaSalaBetValue").value;
            if (defaultBet > 0 && salaNombre != "") {
                dataToSend.salaNombre = salaNombre;
                stompClient.send("/app/createSala." + salaNombre + "." + defaultBet, {}, null);
            }
            else {
                Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: 'Datos de sala incorrectos!',
                });
            }

            //postSala(salaNombre).then(getSalas);
        },
        updateTable: function () {
            getSalas();
        },
        init: function () {
            var socket = new SockJS('/stompendpoint');
            stompClient = Stomp.over(socket);
            usuario = cookieModule.getCookies("usuario");

            stompClient.connect({}, function (frame) {
                console.log('Connected: ' + frame);
                stompClient.subscribe('/topic/salas', function (eventbody) {
                    updateTableSalas(JSON.parse(eventbody.body));
                });

                stompClient.subscribe('/topic/ganancias/' + usuario, function (eventbody) {
                    $.notify("Ha ganado $" + eventbody.body, "success");
                });
            });
        },
        leave: function () {
            stompClient.send("/app/quitarSala/" + dataToSend.salaNombre + "/" + userEmail, {}, null);
        }

    };


})();