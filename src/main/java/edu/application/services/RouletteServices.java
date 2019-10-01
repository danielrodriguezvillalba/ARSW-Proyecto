/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.application.services;

import edu.application.Persistence.RoulettePersistence;
import edu.application.model.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author 2115253
 */
@Service
public class RouletteServices {
    
    @Autowired
    RoulettePersistence rtp=null;
    
    public void recargarSaldoUsuario(Usuario us, float recarga){
        rtp.alterarSaldo(us,recarga);
    }
}
