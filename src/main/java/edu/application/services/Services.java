/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.application.services;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public interface Services {
    
    public ArrayList<Object> allElements();
    
    public Object getElement( Object obj) throws Exception;
}
