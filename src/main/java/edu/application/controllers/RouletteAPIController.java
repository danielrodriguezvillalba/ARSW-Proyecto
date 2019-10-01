package edu.application.controllers;


import edu.application.model.Usuario;
import edu.application.services.RouletteServices;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    RouletteServices services = null;
    
        
    
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
    
    
}