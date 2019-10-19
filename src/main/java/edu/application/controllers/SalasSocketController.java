package edu.application.controllers;


import edu.application.Exceptions.RoulettePersistenceException;
import edu.application.model.Sala;
import edu.application.services.impl.SalasServices;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.util.ArrayList;

@Controller
public class SalasSocketController {

    @Autowired
    private SalasServices salasServices;

    @Autowired
    SimpMessagingTemplate mgt;

    @MessageMapping("/joinSala.{salaNombre}")
    public void joinSala(String dataToSend, @DestinationVariable String salaNombre) throws RoulettePersistenceException {
        JSONObject obj = new JSONObject(dataToSend);
        salasServices.addUsuario(salaNombre, obj.getString("usuario"));
        mgt.convertAndSend("/topic/salas", salasServices.createSalasListResponse().toString());
    }

    @MessageMapping("/createSala.{salaNombre}.{nuevaSalaBetValue}")
    public  void createSala(@DestinationVariable String salaNombre, @DestinationVariable Double nuevaSalaBetValue) throws RoulettePersistenceException{
        salasServices.crearSala(salaNombre, nuevaSalaBetValue);
        mgt.convertAndSend("/topic/salas", salasServices.createSalasListResponse().toString());
    }

    @MessageMapping("/apostar.{salaNombre}.{userEmail}.{casillero}")
    public void apostarSala(@DestinationVariable String salaNombre, @DestinationVariable String userEmail, @DestinationVariable String casillero){

    }

}
