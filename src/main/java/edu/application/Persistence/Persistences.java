/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.application.persistence;
import edu.application.exceptions.RoulettePersistenceException;

/**
 *
 * @author danie
 */
public interface Persistences {
    
    public void realizaConexion() throws RoulettePersistenceException;
    
}
