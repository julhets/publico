package br.com.controladores;

import br.com.entidades.Usuario;
import br.com.util.HibernateUtil;
import org.hamcrest.core.AnyOf;
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
public class UsuarioBeanTest {

    private SessionFactory sf;

    public UsuarioBeanTest() {
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
    public void testInserirNovoUsuario() throws Exception {
        System.out.println("inserirNovoUsuario");

        //testa se um usuário é inserido no BD corretamente;
        //é esperado um dos valores 1, 2 ou 3 pois o método de inserção foi chamado 3 vezes nesta classe e como o hibernate cria a 
        //PK automaticamente, iniciando pelo valor 1, só será possível que o método retorne um dos três métodos;
        UsuarioBean instance = new UsuarioBean();
        long result = instance.inserirNovoUsuario();
        assert result == 1 || result == 2 || result == 3;
    }

    @Test
    public void testAlterarUsuario() throws Exception {
        System.out.println("alterarUsuario");

        //cria um usuário e o recupera para fazer alterações e validar se essas alterações foram realizadas
        UsuarioBean instance = new UsuarioBean();

        long idUsuario = instance.inserirNovoUsuario();
        String nome = "novoNome";
        String email = "novoEmail";

        instance.alterarUsuario(idUsuario, nome, email);
    }

    @Test
    public void testUsuarioJaInformouNomeEEmail() throws Exception {
        System.out.println("usuarioJaInformouNomeEEmail");

        //criamos um usuário em branco e alteramos o nome email do mesmo;
        //em seguida chamamos o método que verifica se este usuário já informou nome e email;
        UsuarioBean instance = new UsuarioBean();
        long idUsuario = instance.inserirNovoUsuario();
        String nome = "nome";
        String email = "email";
        instance.alterarUsuario(idUsuario, nome, email);

        boolean expResult = true;
        boolean result = instance.usuarioJaInformouNomeEEmail(idUsuario);
        assertEquals(expResult, result);
    }

    @Test
    public void testUsuarioValido() throws Exception {
        System.out.println("usuarioValido");

        //criamos um usuário em branco e, em seguida,  verificamos se o mesmo é válido;
        UsuarioBean instance = new UsuarioBean();
        long idUsuario = instance.inserirNovoUsuario();
        boolean expResult = true;
        boolean result = instance.usuarioValido(idUsuario);
        assertEquals(expResult, result);
    }

    @Test
    public void testGetUsuario() throws Exception {
        System.out.println("getUsuario");

        //criamos um usuário e o recuperamos através do método getUsuario();
        UsuarioBean instance = new UsuarioBean();
        long idUsuario = instance.inserirNovoUsuario();

        long expResult = idUsuario;
        Usuario result = instance.getUsuario(idUsuario);
        assertEquals(expResult, result.getIdUsuario());
    }
}
