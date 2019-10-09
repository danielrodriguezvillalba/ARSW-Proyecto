/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.application.services.impl;
import edu.application.model.Sala;
import edu.application.model.Usuario;
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

    public SalasServices(){
        crearSala("Sala 1");
        crearSala("Sala 2");
    }
    
    @Override
    public ArrayList<Object> allElements() {

        return salas;
    }

    @Override
    public Object getElement(Object obj) {
        Sala temp = null;
        for (Object sala : salas) {
            Sala act = (Sala)sala;
            if(act.getNombre().equals(obj.toString())){
                temp = act;
            }
        }
        return temp;
    }
    
    public void crearSala(String nombre){
        Sala nuevaSala = new Sala(nombre);
        salas.add(nuevaSala);
    }

    public void addUsuario(String nombre, Usuario us) {
        Sala s = (Sala) getElement(nombre);
        s.inserteUsuario(us);
    }

    public boolean containsUsuario(String nombre, Usuario us){
        Sala s = (Sala) getElement(nombre);
        return s.containsUsuario(us);
    }


      
}
