/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controladores;

import br.com.entidades.Avaliacao;
import br.com.entidades.Livro;
import br.com.entidades.Usuario;
import br.com.util.HibernateUtil;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Júlio
 */
public class AvaliacaoBeanTest {

    private SessionFactory sf;

    public AvaliacaoBeanTest() {
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
    public void testGravarAvaliacao() throws Exception {
        System.out.println("gravarAvaliacao");

        //testa o cadastro de uma avaliação;
        AvaliacaoBean instance = new AvaliacaoBean();

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
