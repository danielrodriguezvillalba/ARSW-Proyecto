package edu.application.controllers;


import edu.application.Exceptions.RoulettePersistenceException;
import edu.application.model.Usuario;
import edu.application.services.impl.SalasServices;
import edu.application.services.impl.UsuarioServices;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import javax.annotation.PostConstruct;
import java.util.Random;

@Controller
public class SalasSocketController {

    @Autowired
    private SalasServices salasServices;

    @Autowired
    private UsuarioServices usuarioServices;

    @Autowired
    private SimpMessagingTemplate mgt;

    private static SimpMessagingTemplate mgt2;

    @PostConstruct
    private void initMgt2 () {
        mgt2 = this.mgt;
    }

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
        mgt.convertAndSend("/topic/userSaldo/"+userEmail, Integer.toString((int)(((Usuario) usuarioServices.getElement(userEmail)).getSaldo())));
        int winningNumber = new Random().nextInt(37);
        mgt.convertAndSend("/topic/apuestas/"+salaNombre, "{\"player\": \"" + userEmail + "\", \"casillero\":"+casillero+"}");
        //mgt.convertAndSend("/topic/startcountdown."+salaNombre,Integer.toString(winningNumber));
        System.out.println("in the function");
    }

    public static void startCountDown(String salaNombre, String winningNumber){
        mgt2.convertAndSend("/topic/startcountdown."+salaNombre,winningNumber);
    }

    public static void sendUpdatedBalance(String userEmail, float value){
        mgt2.convertAndSend("/topic/userSaldo/"+userEmail, (int) value);
    }

}
