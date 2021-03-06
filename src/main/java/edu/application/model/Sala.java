/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.application.model;

import edu.application.exceptions.RoulettePersistenceException;
import edu.application.controllers.SalasSocketController;
import edu.application.services.impl.UsuarioServices;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 2115253
 */

/*@Component
@Scope("prototype")*/

public class Sala extends Thread{

    private UsuarioServices usuarioServices = new UsuarioServices();

    private static final int maxJugadores = 5;
    private static final int tiempoEspera = 15;
    private final String[] numbers = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12",
        "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29",
        "30", "31", "32", "33", "34", "35", "36", "00", "1to18", "19to36", "even", "odd", "red", "black", "2to1-1",
        "2to1-2", "2to1-3", "1to12", "13to24", "25to36"};
    private int numJugadores;
    private HistorialJugadas historial;
    protected Map< Usuario, Apuesta> apuestas;
    protected String Nombre , numeroGanador;
    private int numero = 90;
    private Random rdn = new Random();
    private Double betValue;

    private HeartBeat heartBeat = new HeartBeat();



    class HeartBeat extends Thread{
        private HashMap<String, Boolean> activeUsers = new HashMap<String, Boolean>();

        private void updateActiveUser(String userEmail) {
            activeUsers.put(userEmail, true);
        }



        @Override
        public void run(){

             for(Map.Entry<Usuario, Apuesta> entrySet : apuestas.entrySet()){
                 activeUsers.put(entrySet.getKey().getCorreo(), false);
             }
            for(int i = 0; i < 3; i++){
                SalasSocketController.heartbeatSala(Nombre);
            }
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            for(Map.Entry<String,Boolean> entrySet: activeUsers.entrySet()){
                if(!entrySet.getValue()){
                    for (Map.Entry<Usuario, Apuesta> entrySet2 : apuestas.entrySet()) {
                        Usuario key = entrySet2.getKey();
                        if(key.getCorreo().equals(entrySet.getKey())){
                            try {
                                if(!entrySet2.getValue().aposto())
                                    elimineUsuario(key);
                            } catch (RuletaException e) {
                                e.printStackTrace();
                            }
                            break;
                        }
                    }

                }
            }
            SalasSocketController.updateSalasList();

        }
    }



    public Sala() {
        historial = new HistorialJugadas();
        apuestas = new ConcurrentHashMap<Usuario, Apuesta>();
        numJugadores = 0;
    }

    public void setSala(String Nombre, Double betValue){
        this.Nombre = Nombre;
        this.betValue = betValue;
    }
    
    public void apuesteNum(Usuario user,String numero){
        Apuesta value = getByCorreo(user.getCorreo());
        value.apostar(numero, betValue);
    }
    
    private Apuesta getByCorreo(String correo) {
        Apuesta res = null;
        for (Map.Entry<Usuario, Apuesta> entrySet : apuestas.entrySet()) {
            Usuario key = entrySet.getKey();
            Apuesta value = entrySet.getValue();
            if (key.getCorreo().equals(correo)){
                res = value;
            }
        }
        return res;
    }
   
    public void setApuestas(Map<Usuario, Apuesta> apuestas) {
        this.apuestas = apuestas;
    }
    

    public String getNombre() {
        return Nombre;
    }

    /**
     * This function adds a user to the room
     * @param us the user to be added
     * @throws RuletaException
     */
    public void inserteUsuario(Usuario us)  {
        if (numJugadores == 5) {
            System.out.println("Sala llena");
        } else {
            if(getByCorreo(us.getCorreo()) == null){
                apuestas.put(us, new Apuesta());
                numJugadores++;
            }
            
        }
    }

    public void updateActiveUser(String userEmail){
        heartBeat.updateActiveUser(userEmail);
    }

    /**
     * This function removes a user from the room
     * @param us the user to be removed
     * @throws RuletaException
     */
    public void elimineUsuario(Usuario us) throws RuletaException {
        Apuesta valor = apuestas.get(us);
        if (valor == null) {
            throw new RuletaException("Usuario a eliminar no encontrado");
        } else {
            apuestas.remove(us);
        }
    }

    /**
     * This function resets the bets of all the players in the room
     */
    public void reinicieApuestas() {
        for (Map.Entry<Usuario, Apuesta> entry : apuestas.entrySet()) {
            Apuesta value = entry.getValue();
            value.reinicie();
        }
    }
    
    public int getNumeroGanador(){
        numero = rdn.nextInt(37);
        //this.run();
        System.out.println(numero);
        return numero;
    }
    
    @Override
    public void run() {
        while (true) {
            long initTime = System.currentTimeMillis();
            while(apuestas.size()==0 || !atLeastOnePlayedBet()){
                try {
                    if(System.currentTimeMillis() - initTime > 10000){
                        heartBeat.run();
                        initTime = System.currentTimeMillis();
                    }
                    Thread.sleep(5);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Sala.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            long startTime = System.currentTimeMillis();
            numeroGanador = Integer.toString(rdn.nextInt(37));

            SalasSocketController.startCountDown(this.Nombre, numeroGanador);
            
            while(System.currentTimeMillis() < startTime + tiempoEspera * 1000){
                this.yield();
            }

            //numeroGanador = Integer.toString(rdn.nextInt(37));

            try {
                Thread.sleep(10000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Sala.class.getName()).log(Level.SEVERE, null, ex);
            }
            for (Map.Entry<Usuario, Apuesta> entry : apuestas.entrySet()) {


                Apuesta apuesta = entry.getValue();
                float ganancias = 0;

                try {
                    System.out.println(apuesta.ganancia(numeroGanador).floatValue());
                    //usuario = usuarioServices.updateSaldoUsuario(usuario, apuesta.ganancia(numeroGanador).floatValue());
                     ganancias = apuesta.ganancia(numeroGanador).floatValue();
                    usuarioServices.updateSaldoUsuarioCache(entry.getKey().getCorreo(), ganancias);
                }catch (Exception e){
                    e.printStackTrace();
                }

                try {
                    usuarioServices.updateCachedUsuarioInDB(entry.getKey().getCorreo());
                    SalasSocketController.sendGanancias(entry.getKey().getCorreo(), ganancias);
                    SalasSocketController.sendUpdatedBalance(entry.getKey().getCorreo(), usuarioServices.getSaldoUsuario(entry.getKey().getCorreo()));
                } catch (RoulettePersistenceException e) {
                    e.printStackTrace();
                }
            }


            heartBeat.run();
            reinicieApuestas();
            SalasSocketController.endOfGame(Nombre);
        }
    }
    
    /**
     * This function verifies if at least one player placed a bet in the room
     * @return returns a boolean indicating if at least a user placed a bet
     */
    public boolean atLeastOnePlayedBet(){
        boolean test = false;
        for(Apuesta a : apuestas.values()){
            if(a.aposto())
                test = true;
        }
        
        return test;
    }
    
    public int getNumeroParticipantes(){
        return apuestas.size();
    }

    public boolean containsUsuario(Usuario us){
        return apuestas.containsKey(us);
    }


    public Double getBetValue() {
        return betValue;
    }

    public void setBetValue(Double betValue) {
        this.betValue = betValue;
    }
}
