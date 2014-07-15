package br.com.controladores;

import br.com.daos.AvaliacaoDAO;
import br.com.entidades.Resultado;
import java.io.IOException;
import java.util.List;
import org.jdom.JDOMException;

/**
 *
 * @author Júlio
 */
public class ResultadoBean {

    public ResultadoBean() {

    }

    //gera uma lista de livros ordenada de forma decrescente em relação ao número de votos;
    public List<Resultado> getResultado() throws JDOMException, IOException {
        AvaliacaoDAO ad = new AvaliacaoDAO();
        return ad.getResultado();
    }
}
