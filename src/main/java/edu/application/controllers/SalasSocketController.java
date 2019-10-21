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
import java.util.Random;

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

    @MessageMapping("/apostar/{salaNombre}/{userEmail}/{casillero}")
    public void apostarSala(@DestinationVariable String salaNombre, @DestinationVariable String userEmail, @DestinationVariable String casillero) throws RoulettePersistenceException {
        salasServices.apostar(salaNombre,userEmail,casillero);
        int winningNumber = new Random().nextInt(37);
        mgt.convertAndSend("/topic/apuestas/"+salaNombre, "{\"player\": \"" + userEmail + "\", \"casillero\":"+casillero+"}");
        mgt.convertAndSend("/topic/startcountdown."+salaNombre,Integer.toString(winningNumber));
        System.out.println("in the function");
    }

    public static void startCountDown(String salaNombre){
        //mgt.convertAndSend("/topic/startcountdown."+salaNombre,"");
    }

}
