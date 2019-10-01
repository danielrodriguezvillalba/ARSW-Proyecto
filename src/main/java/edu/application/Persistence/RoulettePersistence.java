/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.application.Persistence;

import edu.application.model.Usuario;
import org.springframework.stereotype.Service;

/**
 *
 * @author sergio
 */
public interface RoulettePersistence {

    public void alterarSaldo(Usuario us, float valor);
    
}
