/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.application.Persistence;

/**
 *
 * @author Usuario
 */
public class RoulettePersistenceException extends Exception{
    
    public RoulettePersistenceException(String message) {
        super(message);
    }

    public RoulettePersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
