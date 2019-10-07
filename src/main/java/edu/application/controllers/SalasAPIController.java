/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.application.controllers;

import edu.application.model.Sala;
import edu.application.services.impl.SalasServices;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author danie
 */
@RestController
@RequestMapping(value = "/Sala")
public class SalasAPIController {
    
    @Autowired
    private SalasServices services;
    
    ArrayList<Sala> salas  = new ArrayList<Sala>();
    
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<?> getSalas() {

        
        try {
            JSONArray response = new JSONArray();
            for(Sala s : salas){
                JSONObject obj = new JSONObject();
                obj.append("nombre", s.getNombre());
                obj.append("participantes", s.getNumeroParticipantes());
                response.put(obj);
            }
            
            return new ResponseEntity<>(response.toString(),HttpStatus.OK);
        } catch (Exception ex) {
            Logger.getLogger(SalasAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error bla bla bla", HttpStatus.FORBIDDEN);
        }
    }
}
