var  inicioModule = (function () {

    var dataToSend ={
        salaNombre: null,
        usuario: null
    }

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

    var getSalas = function () {
        var getPromise = $.get("/Salas");

        getPromise.then(
            function (data) {
                var obj = JSON.parse(data);
                $("#salasTable tbody").empty();
                obj.map(function(val, index){
                    console.log(val + " " + index);
                    var toAdd = '<tr><td>' + val.nombre + '</td><td>' + val.participantes + '</td><td><button type="button" class="btn btn-secondary" ' +
                        'onclick="inicioModule.joinSala(this.value)" value="'+ val.nombre + '">Join </button></td></tr>';
                    $("#salasTable tbody").append(toAdd);
                })
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


    return{
        apostar: function (casilleroVal) {

        },

        joinSala: function (salaNombre){
            dataToSend.salaNombre = salaNombre;
            dataToSend.usuario = cookieModule.getCookies("usuario");

            putUsuarioSala().then(getSalas).then(function () {
                document.getElementById("main").style.display ='';
                document.getElementById("Welcome").style.display ='none';
                document.getElementById("tableNombre").innerHTML = salaNombre;
            });
        },
        
        nuevaSala: function () {
            var salaNombre = document.getElementById("nuevaSalaNombre").value;
            dataToSend.salaNombre = salaNombre;
            postSala(salaNombre).then(getSalas);
        },

        updateTable: function () {
            getSalas();
        }


    };


})();