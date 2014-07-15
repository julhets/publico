/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.daos;

import br.com.entidades.Usuario;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Júlio
 */
public class UsuarioDAO extends GenericDAO {

    public UsuarioDAO() {

    }

    //registra um novo usuário e retorna o id do registro gravado para ser setado no cookie do navegador do usuário;
    public long gravarNovoUsuario(Usuario u) {
        try {
            getSession().save(u);
            getSession().flush();
            long retorno = u.getIdUsuario();
            return retorno;

        } catch (HibernateException he) {
            System.out.println(
                    "Generic Persistence -> Erro na inserção :" + he.getMessage());
            throw he;
        }
    }

}
