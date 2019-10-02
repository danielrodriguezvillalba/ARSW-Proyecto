/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var module =(function(){
    var usuario;
    var password;
    
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
            
            $.post("/addUser/"+name+"/"+lastname+"/"+email+"/"+password1+"/"+TaxID,function(data){
                
            });
        }
    };
})();
