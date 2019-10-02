/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.application.services;

import edu.application.persistence.RoulettePersistence;
import edu.application.model.Usuario;
import edu.application.persistence.RoulettePersistenceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author 2115253
 */
@Service
public class RouletteServices {
    
    
    @Autowired
    RoulettePersistence rtp = null;
    
    public void recargarSaldoUsuario(Usuario us, float recarga){
        rtp.alterarSaldo(us,recarga);
    }
    
    public String ConsultarUsuario(String user) throws RoulettePersistenceException{
        return rtp.ConsultarUsuario(user);
    }
    
    public void insertarUsuario(Usuario user){
        rtp.insertarUsuario(user);
    }
    
   
}
