package application;


import static org.junit.Assert.assertEquals;


import edu.application.model.Sala;
import edu.application.model.Usuario;
import org.junit.Test;

public class SalaTest {
    @Test
    public void testReinicie(){
        Sala s = new Sala();
        s.setSala("Test", 1000.0);
        Usuario us = new Usuario(1, "TestNombre", "TestApellido", "test@test.com", "12345");

        s.inserteUsuario(us);
        s.apuesteNum(us,"3");

        assertEquals("The bet isn't recorded", true, s.atLeastOnePlayedBet());

        s.reinicieApuestas();

        assertEquals("bets aren't reinitialized", false, s.atLeastOnePlayedBet());

    }

}
