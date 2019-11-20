package edu.application.services.impl;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import edu.application.Exceptions.RoulettePersistenceException;
import edu.application.Persistence.impl.UsuarioPersistence;
import edu.application.cache.UsuarioCache;
import edu.application.model.Usuario;
import edu.application.model.Sala;
import edu.application.services.Services;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 *
 * @author 2115253
 */
@Service
public class UsuarioServices implements Services{
    
    @Autowired
    private UsuarioCache usuarioCache = null;

    @Autowired
    private UsuarioPersistence rtp = null;


    public UsuarioServices(){
        rtp = new UsuarioPersistence();
        usuarioCache = new UsuarioCache();
    };

    public void updateCachedUsuarioInDB(String email) throws RoulettePersistenceException {
        if(!usuarioCache.containsUsuario(email)){
            usuarioCache.putUsuarioCache(rtp.getUsuario(email));
        }
        rtp.updateUsuario(usuarioCache.getCachedUsuario(email));
    }


    public Usuario getCachedUsuario(String email) throws RoulettePersistenceException {
        if(!usuarioCache.containsUsuario(email)){
            usuarioCache.putUsuarioCache(rtp.getUsuario(email));
        }
        return usuarioCache.getCachedUsuario(email);
    }

    public float getSaldoUsuario(String email) throws RoulettePersistenceException {
        if(!usuarioCache.containsUsuario(email)){
            usuarioCache.putUsuarioCache(rtp.getUsuario(email));
        }
        return usuarioCache.getCachedUsuario(email).getSaldo();
    }

    public void updateSaldoUsuarioCache(String email, float value) throws RoulettePersistenceException {
        if(!usuarioCache.containsUsuario(email)){
            usuarioCache.putUsuarioCache(rtp.getUsuario(email));
        }
        usuarioCache.updateUsuarioSaldo(email,value);
    }

    public Usuario updateSaldoUsuario(Usuario us, float value){

        rtp.alterarSaldo(us,value);
        return us;
    }


    /**
    public String ConsultarUsuario(String user) throws RoulettePersistenceException{
        return rtp.ConsultarUsuario(user);
    }*/
    public void insertarUsuario(Usuario user){
        rtp.insertarUsuario(user);
    }


    @Override
    public ArrayList<Object> allElements() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public Object getElement(Object obj) throws RoulettePersistenceException {
        return rtp.getUsuario((String) obj);
        
    }

    public void updatePassword(Usuario us, String psswdHash){
        us.setContra(psswdHash);
        rtp.updateUsuario(us);

    }
    
    public void apostar(Usuario us , String numero, Sala sala){
        rtp.alterarSaldo(us, -100);
        sala.apuesteNum(us, numero);
    }
    
    
            
    
   
}
