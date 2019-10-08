/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.application.controllers;

import edu.application.model.Sala;
import edu.application.services.impl.SalasServices;
import java.util.ArrayList;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author danie
 */
@RestController
@RequestMapping(value = "/Salas")
public class SalasAPIController {
    
    @Autowired
    private SalasServices services;
    
       
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getSalas() {

        ArrayList<Object> salas =  services.allElements();
        try {
            JSONArray response = new JSONArray();
            for(Object s : salas){
                Sala temp = (Sala)s; 
                JSONObject obj = new JSONObject();
                obj.append("nombre", temp.getNombre());
                obj.append("participantes", temp.getNumeroParticipantes());
                response.put(obj);
            }
            
            return new ResponseEntity<>(response.toString(),HttpStatus.OK);
        } catch (Exception ex) {
            Logger.getLogger(SalasAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error bla bla bla", HttpStatus.FORBIDDEN);
        }
    }
    
    
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> crearSala(@RequestBody String nombre){
            try {
            services.crearSala(nombre);
            return new ResponseEntity<>("OK", HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>("ERROR AL CREAR SALA", HttpStatus.FORBIDDEN);
        }
        
    }

    @RequestMapping(method = RequestMethod.GET, path = "/{salaNombre}")
    public ResponseEntity<?> getSala(@PathVariable String salaNombre){

        try{

            return new ResponseEntity<>("OK", HttpStatus.OK);
        }catch (Exception e){

            return new ResponseEntity<>("NOT FOUND", HttpStatus.NOT_FOUND);
        }
    }
}
