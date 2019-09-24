package application;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

import edu.application.model.*;

/**
 * @author Tylones
 */
public class ApuestaTest{

    @Test
    public void testBet(){
        Usuario u = new Usuario(1,"TestUserName","TestUserSurname","user@test.com", "abcd");
        u.setSaldo(1000000);

        Apuesta apuesta = new Apuesta();

        // Betting with a unique number => winnings = bet*36 
        apuesta.apostar("1", 10.0);
        assertEquals("Betting with a unique number not good", Double.valueOf(360.0), apuesta.ganancia("1"));

        // Betting with a unique number and 2to1 
        apuesta.apostar("2to1-1", 10.0);
        assertEquals("Betting with a unique number and 2to1 not good", Double.valueOf(360.0+20), apuesta.ganancia("1"));

        // Betting with a unique number and 2to1 and red
        apuesta.apostar("red", 10.0);
        assertEquals("Betting with a unique number and 2to1 and red not good", Double.valueOf(360.0+20), apuesta.ganancia("1"));

        // Betting with a unique number and 2to1 and red and black (black loosing)
        apuesta.apostar("red", 10.0);
        assertEquals("Betting with a unique number and 2to1 and red and black (black loosing) not good", Double.valueOf(360.0+20), apuesta.ganancia("1"));

        // Betting on two numbers
        apuesta.apostar("2", 10.0);
        assertEquals("Betting with two numbers not good", Double.valueOf(360.0+20), apuesta.ganancia("1"));

        // Nothing wins
        assertEquals("Nothing wins not good", Double.valueOf(360.0+20), apuesta.ganancia("00"));

        // Betting on 1to18
        apuesta.apostar("1to18", 10.0);
        assertEquals("Betting on 1to18 not good", Double.valueOf(360.0+40.0), apuesta.ganancia("1"));

        // Betting on 19to36
    }

}