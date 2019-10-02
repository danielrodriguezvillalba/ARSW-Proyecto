/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var module =(function(){
    var usuario;
    var password;
    var name;
    var lastname;
    var TaxID;
    var email;
    var password1;
    
    return{
        init:function (){
        },
        
        login: function(){
            usuario = document.getElementById("username").value;
            password = document.getElementById("password").value;
            console.log("entro al login"+usuario);
            $.get("/ruleta/users/"+ usuario+".", function (data){
                var contra = data;
                console.log("entro al login"+usuario);
                console.log("entro al contra "+contra);
                if(password == contra){
                    //location.href ="http://localhost:8080/inicio.html";
                    alert("Usuario identificado exitosamente");
                }else{
                    alert("Verifique los datos");
                }
            });
        },
        
        insert: function(){
            
            name = document.getElementById("username").value;
            lastname = document.getElementById("lastname").value;
            TaxID = document.getElementById("TaxID").value;
            email = document.getElementById("email").value;
            password1 = document.getElementById("password").value;            
            
            var newUser = "{\"id\":"+name+ ",\""+lastname+ ",\""+TaxID++ ",\""+email+ ",\""+password1+"}";
            console.log(newUser);
            var crear = $.ajax({
                url: "/ruleta/addUser",
                type: 'POST',
                data: newUser,
                contentType: "application/json"
            });
            crear.then(
                    function () {
                        module.login();
                    },
        }
    };
})();
