/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.util.HashMap;
import java.util.Map;

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
    private Map< String, Apuesta> apuestas;

    public Sala() {
        historial = new HistorialJugadas();
        apuestas = new HashMap< String, Apuesta>();
        numJugadores = 0;
    }

    public void inserteUsuario(Usuario us) throws RuletaException {
        if (numJugadores == 5) {
            throw new RuletaException("Sala llena");
        } else {
            apuestas.put(us.getCorreo(), new Apuesta());
            numJugadores++;
        }
    }

    public void elimineUsuario(Usuario us) throws RuletaException {
        Apuesta valor = apuestas.get(us.getCorreo());
        if (valor == null) {
            throw new RuletaException("Usuario a eliminar no encontrado");
        } else {
            apuestas.remove(us.getCorreo());
        }
    }

    public void reinicieApuestas() {
        for (Map.Entry<String, Apuesta> entry : apuestas.entrySet()) {
            Apuesta value = entry.getValue();
            value.reinicie();
        }
    }

    @Override
    public void run() {
        while (true) {
            
        }
    }
}
