var manageModule = (function () {

    var dataToSend = {
        mail: null,
        amount: null,
        newPassword: null,
        oldPassword: null
    };

    var getUser = function () {
        var getPromise = $.get("/ruleta/Users/"+dataToSend.mail);

        getPromise.then(
            function (data) {
                console.log(data);
            },

            function () {
                console.log('User not found');
            }
        );

        return getPromise;
    };

    var putUserSaldo = function () {
        var putPromise = $.ajax({
            url:"/ruleta/Users/" + dataToSend.mail,
            type: 'PUT',
            data: JSON.stringify(dataToSend),
            contentType: "application/json",
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

        updateSaldo: function () {
            dataToSend.amount = document.getElementById("saldoRecargar").value;
            dataToSend.mail = cookieModule.getCookies("usuario");

            putUserSaldo().then(getUser).then(function (data) {
                document.getElementById("saldoHeader").innerHTML = data.saldo;
                dataToSend.newPassword=null;
                dataToSend.oldPassword=null;
                dataToSend.amount=null;
                document.getElementById("saldoRecargar").value='';
            })

        },

        updatePassword: function(){
            dataToSend.newPassword = document.getElementById("newPassword").value;
            dataToSend.oldPassword = document.getElementById("oldPassword").value;
            dataToSend.mail = cookieModule.getCookies("usuario");

            putUserSaldo().then(getUser).then(function (data) {
                document.getElementById("saldoHeader").innerHTML = data.saldo;
                dataToSend.newPassword=null;
                dataToSend.oldPassword=null;
                dataToSend.amount=null;
                document.getElementById("newPassword").value='';
                document.getElementById("oldPassword").value='';
            })
        },

        getSaldo: function () {
            dataToSend.mail = cookieModule.getCookies("usuario");

            getUser().then(function (data) {
                document.getElementById("saldoHeader").innerHTML = data.saldo;
            })
        }


    };





})();