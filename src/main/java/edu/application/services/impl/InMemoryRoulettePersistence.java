/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.application.services.impl;

import edu.application.persistence.RoulettePersistence;
import edu.application.model.Usuario;
import edu.application.persistence.impl.RouletteDB;
import edu.application.persistence.RoulettePersistenceException;
import org.springframework.stereotype.Service;

/**
 *
 * @author 2115253
 */
@Service
public class InMemoryRoulettePersistence implements RoulettePersistence{
    
    RouletteDB rb; 

    @Override
    public void alterarSaldo(Usuario us, float valor) {
        us.setSaldo(valor);
    }

    @Override
    public void realizaConexion() throws RoulettePersistenceException {
        rb.realizaConexion();
    }

    @Override
    public void insertarUsuario(Usuario user) {
        rb.insertarUsuario(user);
    }

    @Override
    public String ConsultarUsuario(String user) throws RoulettePersistenceException {
        return rb.ConsultarUsuario(user);
    }
    
}
