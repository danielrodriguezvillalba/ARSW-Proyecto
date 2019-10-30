package edu.application.services.impl;
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import edu.application.Exceptions.RoulettePersistenceException;
import edu.application.model.Sala;
import edu.application.model.Usuario;
import edu.application.services.Services;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author danie
 */
@Service
public class SalasServices implements Services {


    ArrayList<Object> salas = new ArrayList<Object>();



    @Autowired
    UsuarioServices usuarioServices;

    public SalasServices(){
        crearSala("1", 1000.0);
        //crearSala("2", 2000.0);
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
    
    public void crearSala(String nombre, Double nuevaSalaBetValue){
        Sala nuevaSala = new Sala();
        nuevaSala.setSala(nombre, nuevaSalaBetValue);
        nuevaSala.start();
        salas.add(nuevaSala);
    }

    public void addUsuario(String nombre, Usuario us) {
        Sala s = (Sala) getElement(nombre);
        s.inserteUsuario(us);
    }

    public void addUsuario(String nombre, String usEmail) throws RoulettePersistenceException {
        Usuario us = (Usuario) usuarioServices.getElement(usEmail);
        Sala s = (Sala) getElement(nombre);
        if(!s.containsUsuario(us))
            s.inserteUsuario(us);
    }

    public boolean containsUsuario(String nombre, Usuario us){
        Sala s = (Sala) getElement(nombre);
        return s.containsUsuario(us);
    }

    public JSONArray createSalasListResponse(){
        JSONArray response = new JSONArray();
        for(Object s : salas){
            Sala temp = (Sala)s;
            JSONObject obj = new JSONObject();
            obj.append("nombre", temp.getNombre());
            obj.append("participantes", temp.getNumeroParticipantes());
            obj.append("betValue", temp.getBetValue());
            response.put(obj);
        }
        return response;
    }

    public int getNumeroGanador(Sala sala){
        return sala.getNumeroGanador();
    }

    public void apostar(String salaNombre, String userEmail, String casillero) throws RoulettePersistenceException {
        Sala s = (Sala) getElement(salaNombre);
        Usuario us = (Usuario) usuarioServices.getElement(userEmail);
        s.apuesteNum(us,casillero);
    }


}
