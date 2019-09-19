/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package application;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * @author 2115253
 */
class Apuesta {
    
    private final String[] numbers = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "10", "11", "12",
        "13", "14", "15", "16", "17", "18", "19", "20", "21", "22", "23", "24", "25", "26", "27", "28", "29",
        "30", "31", "32", "33", "34", "35", "36", "00"};
    private Map<String, Double> apuestas; 
   
    
    public Apuesta() {
        apuestas = new HashMap<String, Double>();
        for(String s : numbers){
            apuestas.put(s, 0.0);
        }
    }
    
    public void reinicie(){
        for(String s : apuestas.keySet()){
            apuestas.put(s, 0.0);
        }
    }
    
    public void apuestar(final String casilla, Double value){
        value += apuestas.get(casilla);
        apuestas.put(casilla, value);
    }
    
    public Double ganancia(){
        
        return 0.0;
    }   
}
