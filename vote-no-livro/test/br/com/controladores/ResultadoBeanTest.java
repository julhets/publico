/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controladores;

import br.com.entidades.Avaliacao;
import br.com.entidades.Livro;
import br.com.entidades.Resultado;
import br.com.entidades.Usuario;
import br.com.util.HibernateUtil;
import java.util.List;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Júlio
 */
public class ResultadoBeanTest {

    private SessionFactory sf;

    public ResultadoBeanTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        sf = HibernateUtil.getSessionFactory();
        sf.getCurrentSession().beginTransaction();
    }

    @After
    public void tearDown() {
        sf.getCurrentSession().getTransaction().commit();
    }

    @Test
    public void testGetResultado() throws Exception {
        System.out.println("getResultado");
        
        //cadastra uma avaliação;
        cadastraAvaliacao();
        
        //como só há uma avaliação registrada, só um registro de resultado deverá ser retornado;        
        ResultadoBean instance = new ResultadoBean();
        int expResult = 1;
        List<Resultado> result = instance.getResultado();
        assertEquals(expResult, result.size());
    }

    private void cadastraAvaliacao() throws Exception {
        AvaliacaoBean instance = new AvaliacaoBean();
        
        //simula o cadastro de uma avaliação;

        Livro livro1 = new Livro();
        livro1.setDescricao("Livro Teste 1");
        livro1.setUrlImg("http://teste1.jpg");

        Livro livro2 = new Livro();
        livro2.setDescricao("Livro Teste 2");
        livro2.setUrlImg("http://teste2.jpg");

        Usuario u = new Usuario();
        u.setNome("Usuário Teste");
        u.setEmail("teste@teste.com.br");

        Avaliacao a = new Avaliacao();
        a.setLivro1(livro1);
        a.setLivro2(livro2);
        a.setUsuario(u);
        a.setAvaliacao(1);
        instance.setAvaliacao(a);

        instance.gravarAvaliacao();
    }
}
