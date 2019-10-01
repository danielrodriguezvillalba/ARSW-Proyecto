/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.application.services.impl;

import edu.application.Persistence.RoulettePersistence;
import edu.application.model.Usuario;
import org.springframework.stereotype.Service;

/**
 *
 * @author 2115253
 */
@Service
public class InMemoryRoulettePersistence implements RoulettePersistence{

    @Override
    public void alterarSaldo(Usuario us, float valor) {
        us.setSaldo(valor);
    }
    
}
