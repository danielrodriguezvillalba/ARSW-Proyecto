var  inicioModule = (function () {

    var waiting = false;
    var chips=new Array();
    var bets=new Array();


    var dataToSend ={
        salaNombre: null,
        usuario: null
    };
    
    var dataToUser ={
        sala: null,
        usuario: null,
        numero: null
    };

    var stompClient = null;


    var postSala = function (salaNombre) {
        var postPromise = $.ajax({
            url:"/Salas",
            type:'POST',
            data:salaNombre,
            contentType: "application/json"
        });

        postPromise.then(
            function(){
                console.info('Post OK');
            },
            function(){
                console.info('Post NOK');
            }
        );

        return postPromise;
    };

    var updateTableSalas = function (data) {
        $("#salasTable tbody").empty();
        data.map(function(val, index){
            console.log(val + " " + index);
            var toAdd = '<tr><td>' + val.nombre + '</td><td>' + val.participantes + '</td><td>' + val.betValue + '</td> <td><button type="button" class="btn btn-secondary" ' +
                'onclick="inicioModule.joinSala(this.value)" value="'+ val.nombre + '">Join </button></td></tr>';
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
            url:'/Salas/'+dataToSend.salaNombre,
            type:'PUT',
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
            url:"/ruleta/apuestaUser",
            type:'POST',
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
        
        reInt: function(min,max){
            return Math.floor(Math.random() * (max - min + 1)) + min;
        },

        setWaiting : function(value){
            waiting = value;
            var ncoins = bets.length;
            for (var i=0;i<ncoins;i++){
                document.body.removeChild(chips[bets[i]].pop());
            }
            bets = new Array();
        },

        apostar: function (casilleroVal) {
            var userEmail = cookieModule.getCookies("usuario");
            dataToUser.numero = casilleroVal;
            dataToUser.sala =  document.getElementById("tableNombre").innerHTML;

            stompClient.send("/app/apostar/"+ dataToSend.salaNombre + "/" + userEmail + "/" +  casilleroVal, {}, null);
            //postApuestaUsuario();
            
            
            var lugar = document.getElementById(casilleroVal);
            var _x =  lugar.offsetLeft;
            var _y = lugar.offsetTop;
            var img = document.createElement('img');
            img.src="../imagen/ficha.png";
            img.style.zIndex="0";
            img.style.position="absolute";

            var rX=Math.floor(Math.random() * (16 - (-16) + 1)) + -16;
            var rY=Math.floor(Math.random() * (16 - (-16) + 1)) + -16;

            img.style.left=(_x+rX)+"px";
            img.style.top=(_y+rY+150)+"px";

            img.style.width="20px";
            img.style.pointerEvents="none";
            document.body.appendChild(img);
            
            if(chips[casilleroVal]==null)chips[casilleroVal]=new Array(0);
            chips[casilleroVal].push(img);
            bets.push(casilleroVal);

        },

        joinSala: function (salaNombre){
            dataToSend.salaNombre = salaNombre;
            dataToSend.usuario = cookieModule.getCookies("usuario");

            stompClient.send("/app/joinSala."+dataToSend.salaNombre, {}, JSON.stringify(dataToSend));
            document.getElementById("main").style.display ='';
            document.getElementById("Welcome").style.display ='none';
            document.getElementById("tableNombre").innerHTML = salaNombre;


            stompClient.subscribe('/topic/salas.'+salaNombre, function (eventbody) {
                updateTableSalas(JSON.parse(eventbody.body));
            });

            stompClient.subscribe('/topic/userSaldo/'+dataToSend.usuario, function (eventbody) {
                document.getElementById("saldoHeader").innerHTML = eventbody.body;
            });

            stompClient.subscribe('/topic/apuestas/'+salaNombre, function (eventbody) {
                console.log('El jugador : ' + (JSON.parse(eventbody.body)).player + ' aposta en el casillero : ' + (JSON.parse(eventbody.body)).casillero);
            });

            stompClient.subscribe('/topic/startcountdown.'+dataToSend.salaNombre, function (eventbody) {
                if(waiting == false){
                    waiting = true;
                    rouletteModule.countdown(eventbody.body);
                }
            });

            /*putUsuarioSala().then(getSalas).then(function () {
                document.getElementById("main").style.display ='';
                document.getElementById("Welcome").style.display ='none';
                document.getElementById("tableNombre").innerHTML = salaNombre;
            });*/
        },
        
        nuevaSala: function () {
            var salaNombre = document.getElementById("nuevaSalaNombre").value;
            var defaultBet = document.getElementById("nuevaSalaBetValue").value;
            dataToSend.salaNombre = salaNombre;
            stompClient.send("/app/createSala."+salaNombre+"."+ defaultBet, {}, null);
            //postSala(salaNombre).then(getSalas);
        },

        updateTable: function () {
            getSalas();
        },

    
        
        init : function () {
            var socket = new SockJS('/stompendpoint');
            stompClient = Stomp.over(socket);

            stompClient.connect({}, function (frame) {
                console.log('Connected: ' + frame);
                stompClient.subscribe('/topic/salas', function (eventbody) {
                    updateTableSalas(JSON.parse(eventbody.body));
                });
            });
        }

    };


})();