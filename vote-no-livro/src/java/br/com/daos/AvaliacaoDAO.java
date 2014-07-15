/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.daos;

import br.com.entidades.Avaliacao;
import br.com.entidades.Livro;
import br.com.entidades.Resultado;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.criterion.Restrictions;
import org.jdom.JDOMException;

/**
 *
 * @author Júlio
 */
public class AvaliacaoDAO extends GenericDAO {
    
    public AvaliacaoDAO() {
        
    }
    
    public List<Avaliacao> listarAvaliacoes(long idUsuario) {
        return getSession().createCriteria(Avaliacao.class).add(Restrictions.eq("usuario.idUsuario", idUsuario)).list();
    }
    
    public List<Resultado> getResultado() throws JDOMException, JDOMException, IOException {
        List<Resultado> listaResultados = new ArrayList();
        List<Object[]> lista = getSession().createSQLQuery(retornaSql("SELECIONA_RESULTADO")).list();
        for (Object[] o : lista) {
            listaResultados.add(new Resultado((Livro) getSession().get(Livro.class, Long.parseLong(o[0].toString())), Integer.parseInt(o[1].toString())));
        }
        return listaResultados;
    }
    
}
