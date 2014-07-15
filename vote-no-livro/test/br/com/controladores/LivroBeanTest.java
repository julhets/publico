/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controladores;

import br.com.daos.UsuarioDAO;
import br.com.entidades.Livro;
import br.com.entidades.Usuario;
import br.com.util.HibernateUtil;
import java.util.List;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

/**
 *
 * @author Júlio
 */
public class LivroBeanTest {
    
    private SessionFactory sf;
    
    public LivroBeanTest() {
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
    public void testGetParLivrosAvaliacao() throws Exception {
        System.out.println("getParLivrosAvaliacao");
        
        //cadastra usuário
        Usuario u = new Usuario();
        u.setIdUsuario(1);
        u.setNome("Usuário Teste");
        u.setEmail("teste@teste.com.br");
        UsuarioDAO ud = new UsuarioDAO();
        ud.gravar(u);
        
        //cadastra livros
        LivroBean livroBean = new LivroBean();
        livroBean.povoarBaseDeLivros();
        
        //verifica se uma lista com dois elementos é retornada
        long idUsuario = u.getIdUsuario();
        int expResult = 2;
        List<Livro> result = livroBean.getParLivrosAvaliacao(idUsuario);
        assertEquals(expResult, result.size());
    }

    @Test
    public void testPovoarBaseDeLivros() throws Exception {
        System.out.println("povoarBaseDeLivros");
        
        //testa o método de povoamento na tabela de livros;
        
        LivroBean instance = new LivroBean();
        instance.povoarBaseDeLivros();
    }
}
