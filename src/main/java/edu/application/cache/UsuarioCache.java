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
        return usuarioMap.get(email);
    }

    public boolean containsUsuario(String email){
        return usuarioMap.containsKey(email);
    }

    public void putUsuarioCache(Usuario us){
        usuarioMap.put(us.getCorreo(),us);
    }

    public void updateUsuarioSaldo(String email, float value){
        float oldValue = usuarioMap.get(email).getSaldo();
        usuarioMap.get(email).setSaldo(oldValue + value);
    }
}
