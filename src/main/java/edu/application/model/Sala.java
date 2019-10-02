/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.application.model;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author 2115253
 */
public class Sala extends Thread {

    private static final int maxJugadores = 5;
    private static final int tiempoEspera = 15;
    private final String[] numbers = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12",
        "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29",
        "30", "31", "32", "33", "34", "35", "36", "00", "1to18", "19to36", "even", "odd", "red", "black", "2to1-1",
        "2to1-2", "2to1-3", "1to12", "13to24", "25to36"};
    private int numJugadores;
    private HistorialJugadas historial;
    private Map< Usuario, Apuesta> apuestas;
    private String Nombre;

    public String getNombre() {
        return Nombre;
    }

    public Sala(String Nombre) {
        historial = new HistorialJugadas();
        apuestas = new HashMap< Usuario, Apuesta>();
        numJugadores = 0;
        this.Nombre = Nombre;
    }

    /**
     * This function adds a user to the room
     * @param us the user to be added
     * @throws RuletaException
     */
    public void inserteUsuario(Usuario us) throws RuletaException {
        if (numJugadores == 5) {
            throw new RuletaException("Sala llena");
        } else {
            apuestas.put(us, new Apuesta());
            numJugadores++;
        }
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

    @Override
    public void run() {
        while (true) {
            while(apuestas.size()==0 || !atLeastOnePlayedBet()){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Sala.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            
            long startTime = System.currentTimeMillis();
            
            while(System.currentTimeMillis() < startTime + tiempoEspera * 1000){
                try {
                    Thread.sleep(500);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Sala.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            Random rdn = new Random();
            int numero = rdn.nextInt(39);
            String numeroGanador;
            if(numero != 38)
                numeroGanador = Integer.toString(numero);
            else
                numeroGanador = "00";
            
            for (Map.Entry<Usuario, Apuesta> entry : apuestas.entrySet()) {
                Usuario usuario = entry.getKey();
                Apuesta apuesta = entry.getValue();
                
                usuario.setSaldo(usuario.getSaldo() + apuesta.ganancia(numeroGanador).floatValue());
            }
            reinicieApuestas();
        }
    }
    
    /**
     * This function verifies if at least one player placed a bet in the room
     * @return returns a boolean indicating if at least a user placed a bet
     */
    private boolean atLeastOnePlayedBet(){
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
}
