/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.application.MyBatisDAO;

import edu.application.DAO.UsuarioDAO;
import edu.application.Persistence.impl.UsuarioPersistence;
import edu.application.model.Usuario;
import edu.application.Exceptions.RoulettePersistenceException;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author danie
 */
public class MyBatisUsuarioDAO implements UsuarioDAO {

    @Autowired
    UsuarioPersistence usPer = null;

    @Override
    public Usuario consultarUsuario(String correo) throws RoulettePersistenceException{
        return usPer.getUsuario(correo);
    }

    @Override
    public void insertarUsuario(int cedula, String nombre, String apellido, String correo, String contra) {
        Usuario user = new Usuario(cedula, nombre, apellido, correo, contra);
        usPer.insertarUsuario(user);
    }

    @Override
    public void updateUsuario(Usuario us) {

    }

}
