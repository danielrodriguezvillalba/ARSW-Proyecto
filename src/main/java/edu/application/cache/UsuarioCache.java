package edu.application.cache;

import edu.application.model.Usuario;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Tylones
 */
@Service
public class UsuarioCache {

    private static Map<String, Usuario> usuarioMap = new HashMap<String, Usuario>();

    public Usuario getCachedUsuario(String email){
        synchronized (usuarioMap){
            return usuarioMap.get(email);
        }
    }

    public boolean containsUsuario(String email){
        synchronized (usuarioMap) {
            return usuarioMap.containsKey(email);
        }
    }

    public void putUsuarioCache(Usuario us){
        synchronized (usuarioMap) {
            usuarioMap.put(us.getCorreo(), us);
        }
    }

    public void updateUsuarioSaldo(String email, float value){
        synchronized (usuarioMap){
            float oldValue = usuarioMap.get(email).getSaldo();
            usuarioMap.get(email).setSaldo(oldValue + value);
        }
    }
}
