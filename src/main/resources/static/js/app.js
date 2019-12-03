/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var module = (function () {

    var user = {
        name: null,
        lastname: null,
        TaxID: null,
        email: null,
        password1: null
    }

    var usuario;
    var password;
    var name;
    var lastname;
    var TaxID;
    var email;
    var password1;



    return{
        login: function () {
            user.email = document.getElementById("username").value;
            user.password = document.getElementById("password").value;
            $.ajax({
                url: "/ruleta/Users",
                type: 'POST',
                data: JSON.stringify(user),
                contentType: 'application/json',
                success: function () {
                    cookieModule.setCookies("usuario", user.email, 0.1);
                    location.href = "inicio.html";
                },
                error: function () {
                    Swal.fire({
                        icon: 'error',
                        title: 'Oops...',
                        text: 'Credenciales incorrectas!',
                    });
                }
            });

        },
        insert: function () {

            user.name = document.getElementById("username").value;
            user.lastname = document.getElementById("lastname").value;
            user.TaxID = document.getElementById("TaxID").value;
            user.email = document.getElementById("email").value;
            user.password1 = document.getElementById("password").value;

            var newUser = "{\"id\":" + TaxID + ",\"nombre\":'" + name + "',\"apellido\":'" + lastname + "',\"correo\":'" + email + "',\"contra\":'" + password1 + "'}";
            console.log(newUser);
            if (user.TaxID > 0) {
                if ((user.email).includes('@') === true) {
                    if ((user.email).includes('.') === true) {
                        Swal.fire(
                                'Operación Exitosa',
                                'Se registró satisfactoriamente!',
                                'success'
                                )
                        var crear = $.ajax({
                            url: "/ruleta/addUser",
                            type: 'POST',
                            data: JSON.stringify(user),
                            contentType: "application/json",
                            success: function () {
                                location.href = "index.html";
                            },
                            error: function () {

                            }


                        });
                        
                    }
                    else {
                        Swal.fire({
                            icon: 'error',
                            title: 'Oops...',
                            text: 'Datos incorrectos!',
                        });
                    }

                }
                else {
                    Swal.fire({
                        icon: 'error',
                        title: 'Oops...',
                        text: 'Datos incorrectos!',
                    });
                }




            }
            else {
                Swal.fire({
                    icon: 'error',
                    title: 'Oops...',
                    text: 'Datos incorrectos!',
                });
            }


        }
    };
})();
