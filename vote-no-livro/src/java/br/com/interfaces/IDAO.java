/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.interfaces;

import java.util.List;

/**
 *
 * @author jetreis2
 */
public interface IDAO {

    void gravar(Object t) throws Exception;

    void alterar(Object t) throws Exception;

    void remover(Object t) throws Exception;

    Object carregar(Long pk, Class classe) throws Exception;

    List<Object> listar(String query) throws Exception;
}
