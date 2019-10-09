/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.application.DAO;

import edu.application.model.Usuario;
import edu.application.Exceptions.RoulettePersistenceException;

/**
 *
 * @author danie
 */
public interface UsuarioDAO {
    
    public Usuario consultarUsuario(String correo)throws RoulettePersistenceException;
    
    void insertarUsuario(int cedula, String nombre, String apellido, String correo,String contra);

    void updateUsuario(Usuario us);
    
}
