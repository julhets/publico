/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.daos;

import br.com.entidades.Livro;
import java.util.List;

/**
 *
 * @author Júlio
 */
public class LivroDAO extends GenericDAO {
    
    public LivroDAO() {
        
    }
    
    public List<Livro> getLivros() throws Exception {
        return listar(retornaSql("SELECIONA_LIVROS_RANDOMICOS"));
    }
}
