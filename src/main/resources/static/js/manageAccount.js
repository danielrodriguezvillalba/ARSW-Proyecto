var manageModule = (function () {

    var dataToSend = {

        mail: null,
        amount: null,
        newPassword: null,
        oldPassword: null

    };

    var getUser = function () {

        var getPromise = $.get("/ruleta/Users/" + dataToSend.mail);

        getPromise.then(
                function (data) {
                },
                function () {
                }
        );

        return getPromise;

    };

    var putUserSaldo = function () {

        var putPromise = $.ajax({
            url: "/ruleta/Users/" + dataToSend.mail,
            type: 'PUT',
            data: JSON.stringify(dataToSend),
            contentType: "application/json",
        });

        putPromise.then(
                function () {
                },
                function () {
                }
        );

        return putPromise;

    };

    var sald = null;

    return{

        updateSaldo: function () {

            dataToSend.amount = document.getElementById("saldoRecargar").value;
            dataToSend.mail = cookieModule.getCookies("usuario");

            if (dataToSend.amount > 4999 && dataToSend.amount < 2147483640) {
                Swal.fire(
                        'Operación Exitosa',
                        'Su recarga ha sido exitosa!',
                        'success'
                        )
                putUserSaldo().then(getUser).then(function (data) {
                    document.getElementById("saldoHeader").innerHTML = data.saldo;
                    dataToSend.newPassword = null;
                    dataToSend.oldPassword = null;
                    dataToSend.amount = null;

                    document.getElementById("saldoRecargar").value = '';
                })
            }

            else if (dataToSend.amount > 0 && dataToSend.amount < 5000){
                Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: 'recarga minima 5000!',
                });
            }

            else {

                Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: 'Monto a recargar incorrecto!',
                });

            }



        },
        updatePassword: function () {

            dataToSend.newPassword = document.getElementById("newPassword").value;
            dataToSend.oldPassword = document.getElementById("oldPassword").value;
            dataToSend.mail = cookieModule.getCookies("usuario");

            putUserSaldo().then(getUser).then(function (data) {
                document.getElementById("saldoHeader").innerHTML = data.saldo;
                dataToSend.newPassword = null;
                dataToSend.oldPassword = null;
                dataToSend.amount = null;
                document.getElementById("newPassword").value = '';
                document.getElementById("oldPassword").value = '';
                Swal.fire(
                        'Operación Exitosa',
                        'Su contraseña ha sido cambiada!',
                        'success'
                        )
            })
        },
        getSaldo: function () {

            dataToSend.mail = cookieModule.getCookies("usuario");

            getUser().then(function (data) {

                sald = data.saldo;
                document.getElementById("saldoHeader").innerHTML = data.saldo;
            });
            return sald;
        }
    };
})();