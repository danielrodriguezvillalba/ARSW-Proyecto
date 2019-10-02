package edu.application.controllers;


import edu.application.model.Usuario;
import edu.application.persistence.RoulettePersistenceException;
import edu.application.services.RouletteServices;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/ruleta")
public class RouletteAPIController{
    
    @Autowired
    private RouletteServices services;
    
    @RequestMapping(method = RequestMethod.PUT, path = "{recarga}")
    public ResponseEntity<?> manejadorInicio() {
        Usuario us = null;
        try {
            services.recargarSaldoUsuario(us,00);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception ex) {
            return new ResponseEntity<>("ERROR 403", HttpStatus.FORBIDDEN);
        }
    }
    
    @RequestMapping(path = "/users/{idUser1}", method = RequestMethod.GET)
    public ResponseEntity<?> getUsers(@PathVariable("idUser1") String idUser1) {
        try {
            //obtener datos que se enviarán a través del API
            return new ResponseEntity<>(services.ConsultarUsuario(idUser1), HttpStatus.ACCEPTED);
        } catch (RoulettePersistenceException ex) {
            Logger.getLogger(RouletteAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error bla bla bla", HttpStatus.NOT_FOUND);
        }
    }
    
    @RequestMapping(path = "/addUser", method = RequestMethod.POST)
    public ResponseEntity<?> InsertUsers(@RequestBody String body) {
        //obtener datos que se enviarán a través del API
        JSONObject obj = new JSONObject(body);
        Usuario us = new Usuario(obj.getInt("TaxID"), obj.getString("name"), obj.getString("lastname"), obj.getString("email"), obj.getString("password1"));
        System.out.println("Va a ingreas");
        try {
            services.insertarUsuario(us);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception ex) {
            Logger.getLogger(RouletteAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error bla bla bla", HttpStatus.FORBIDDEN);
        }
    }
    
    
    
    
}