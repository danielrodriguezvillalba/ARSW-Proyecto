/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.application.services.impl;
import edu.application.model.Sala;
import edu.application.services.Services;
import java.util.ArrayList;
import org.springframework.stereotype.Service;

/**
 *
 * @author danie
 */
@Service
public class SalasServices implements Services{
    
    ArrayList<Object> salas = new ArrayList<Object>();
    
    @Override
    public ArrayList<Object> allElements() {
        return salas;
    }

    @Override
    public Object getElement(Object obj) {
        Sala temp = null;
        for (Object sala : salas) {
            Sala act = (Sala)sala;
            if(act.getNombre() == (String)obj){
                temp = act;
            }
        }
        return temp;
    }
    
    public void crearSala(String nombre){
        Sala nuevaSala = new Sala(nombre);
        salas.add(nuevaSala);
    }
      
}
