/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.application.services.impl;

import edu.application.Exceptions.RoulettePersistenceException;
import edu.application.Persistence.impl.UsuarioPersistence;
import edu.application.model.Usuario;

import edu.application.services.Services;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author 2115253
 */
@Service
public class UsuarioServices implements Services{
    
    
    @Autowired
    UsuarioPersistence rtp = null;
    
    public void recargarSaldoUsuario(Usuario us, float recarga){
        rtp.alterarSaldo(us,recarga);
    }
    /**
    public String ConsultarUsuario(String user) throws RoulettePersistenceException{
        return rtp.ConsultarUsuario(user);
    }*/
    
    public void insertarUsuario(Usuario user){
        rtp.insertarUsuario(user);
    }

    @Override
    public Set<Object> allElements() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public Object getElement(Object obj) throws RoulettePersistenceException {
        return rtp.consultarUsuario((String) obj);
        
    }
    
   
}
