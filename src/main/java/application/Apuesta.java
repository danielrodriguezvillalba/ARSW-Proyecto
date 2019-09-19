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
        "30", "31", "32", "33", "34", "35", "36", "00", "1to18", "19to36", "even", "odd", "red", "black", "2to1-1",
        "2to1-2", "2to1-3", "1to12", "13to24", "25to36"};
    
    private final String[] negro = {"2","4","6", "8","10", "11", "13","15", 
        "17", "20", "22", "24", "26", "28", "29", "31", "33", "35"};
    
    private final String[] verde = {"0", "00"};
    
    private final String[] twotoone1 = {"1", "4", "7", "10", "13", "16", "19", "22", "25", "28", "31", "34"};
    
    private final String[] twotoone2 = {"2", "5", "8", "11", "14", "17", "20", "23", "26", "29", "32", "35"};
    
    private final String[] twotoone3 = {"3", "6", "9", "12", "15", "18", "21", "24", "27", "30", "33", "36"};
    
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
    
    public Double ganancia(final String numeroGanador){
        Double valor = 0.0;
        valor += apuestas.get(numeroGanador) * 36;
        int numero = Integer.getInteger(numeroGanador);
        if(numero <= 1 && numero <=18)
            valor += apuestas.get("1to18") * 2;
        else if(numero <= 19 && numero <=36)
            valor += apuestas.get("19to36") * 2;
        if(numero <= 1 && numero <=12)
            valor += apuestas.get("1to12") * 3;
        else if(numero <= 13 && numero <=24)
            valor += apuestas.get("13to24") * 3;
        else if(numero <= 25 && numero <=36)
            valor += apuestas.get("25to36") * 3;
        if(numero!=0 && numero % 2 == 0)
            valor += apuestas.get("odd") * 2;
        else if(numero!=0 && numero % 2 != 0)
            valor += apuestas.get("even") * 2;
        
        if(stringArrayContains(negro, numeroGanador))
            valor += apuestas.get("black");
        else if(!stringArrayContains(verde, numeroGanador))
            valor += apuestas.get("red");
        
        if(stringArrayContains(twotoone1, numeroGanador))
            valor += apuestas.get("2to1-1");
        
        else if(stringArrayContains(twotoone2, numeroGanador))
            valor += apuestas.get("2to1-2");
        
        else if(stringArrayContains(twotoone3, numeroGanador))
            valor += apuestas.get("2to1-3");
        
        
        return valor;
    }   
    
    public boolean stringArrayContains(String[] array, String valor){
        boolean test = false;
        for (int i = 0; i < array.length; i++) {
            if(array[i].equals(valor))
                test = true;
        }
        return test;
    }
}
