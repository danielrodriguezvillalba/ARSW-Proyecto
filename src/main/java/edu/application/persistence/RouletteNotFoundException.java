/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.application.persistence;

/**
 *
 * @author Usuario
 */
public class RouletteNotFoundException extends Exception{
    
    public RouletteNotFoundException(String message) {
        super(message);
    }

    public RouletteNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
