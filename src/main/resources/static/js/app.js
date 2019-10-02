/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var module =(function(){
    
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
        
        login: function(){
            usuario = document.getElementById("username").value;
            password = document.getElementById("password").value;
            console.log("entro al login"+usuario);
            $.get("/ruleta/users/"+ usuario+".", function (data){
                var contra = data;
                console.log("entro al login"+usuario);
                console.log("entro al contra "+contra);
                if(password == contra){
                    location.href ="inicio.html";
                }else{
                    alert("Credenciales incorectas");
                }
            });
            
        },
        
        insert: function(){
            
            user.name = document.getElementById("username").value;
            user.lastname = document.getElementById("lastname").value;
            user.TaxID = document.getElementById("TaxID").value;
            user.email = document.getElementById("email").value;
            user.password1 = document.getElementById("password").value;            
            
            var newUser = "{\"id\":"+TaxID+",\"nombre\":'"+name+"',\"apellido\":'"+lastname+"',\"correo\":'"+email+"',\"contra\":'"+password1+"'}";
            console.log(newUser);
            var crear = $.ajax({
                url: "/ruleta/addUser",
                type: 'POST',
                data: JSON.stringify(user),
                contentType: "application/json",
                success: function(){
                    location.href ="index.html";
                },
                error: function(){
                    
                }
            });
        }   
    };
})();
