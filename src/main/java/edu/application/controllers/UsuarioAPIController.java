package edu.application.controllers;


import edu.application.model.Sala;
import edu.application.model.Usuario;
import edu.application.Exceptions.RoulettePersistenceException;
import edu.application.services.Services;
import edu.application.services.impl.UsuarioServices;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
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

@RestController
@RequestMapping(value = "/ruleta")
public class UsuarioAPIController{
    
    @Autowired
    private UsuarioServices services = null;
    
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

    @RequestMapping(path = "/Users", method = RequestMethod.POST)
    public ResponseEntity<?> getUsers(@RequestBody String body) {
        try {
            //obtener datos que se enviarán a través del API
            JSONObject obj = new JSONObject(body);
            String username = obj.getString("email");
            String password = obj.getString("password");
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] bytes = digest.digest(password.getBytes());
            StringBuilder sb = new StringBuilder();
            for(int i = 0; i < bytes.length; i++){
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }
            String psswdHash = sb.toString();
            Usuario u = (Usuario) services.getElement(username);
            if(u.getContra().equals(psswdHash))
                return new ResponseEntity<>(services.getElement(username), HttpStatus.OK);
            else
                return new ResponseEntity<>("Wrong password", HttpStatus.UNAUTHORIZED);

        } catch (RoulettePersistenceException | NoSuchAlgorithmException ex) {
            Logger.getLogger(UsuarioAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error bla bla bla", HttpStatus.NOT_FOUND);
        }
    }
    
    @RequestMapping(path = "/addUser", method = RequestMethod.POST)
    public ResponseEntity<?> InsertUsers(@RequestBody String body) throws NoSuchAlgorithmException {
        //obtener datos que se enviarán a través del API
        JSONObject obj = new JSONObject(body);
        String password = obj.getString("password1");
        MessageDigest digest = MessageDigest.getInstance("SHA-256");
        byte[] bytes = digest.digest(password.getBytes());
        StringBuilder sb = new StringBuilder();
        for(int i = 0; i < bytes.length; i++){
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        String psswdHash = sb.toString();
        Usuario us = new Usuario(obj.getInt("TaxID"), obj.getString("name"), obj.getString("lastname"), obj.getString("email"), psswdHash);
        System.out.println("Va a ingreas");
        try {
            services.insertarUsuario(us);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (Exception ex) {
            Logger.getLogger(UsuarioAPIController.class.getName()).log(Level.SEVERE, null, ex);
            return new ResponseEntity<>("Error bla bla bla", HttpStatus.FORBIDDEN);
        }
    }

    
}