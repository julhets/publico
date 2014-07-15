package br.com.controladores;

import br.com.daos.UsuarioDAO;
import br.com.entidades.Usuario;

/**
 *
 * @author Júlio
 */
public class UsuarioBean {

    private final UsuarioDAO usuarioDao;

    public UsuarioBean() {
        usuarioDao = new UsuarioDAO();
    }

    public long inserirNovoUsuario() throws Exception {
        Usuario usuario = new Usuario();
        return usuarioDao.gravarNovoUsuario(usuario);
    }

    public void alterarUsuario(long idUsuario, String nome, String email) throws Exception {
        Usuario usuario = (Usuario) usuarioDao.carregar(idUsuario, Usuario.class);
        usuario.setNome(nome);
        usuario.setEmail(email);
        usuarioDao.alterar(usuario);
    }

    //verifica se o usuário já realizou todas avaliações das possíveis combinações entre os filmes;
    //se ele já informou o nome e e-mail, é porque já realizou;
    public boolean usuarioJaInformouNomeEEmail(long idUsuario) throws Exception {
        Usuario u = (Usuario) usuarioDao.carregar(idUsuario, Usuario.class);
        if (u != null && u.getEmail() != null) {
            return true;
        }
        return false;
    }

    //verifica se o usuário existe;
    public boolean usuarioValido(long idUsuario) throws Exception {
        Usuario u = (Usuario) usuarioDao.carregar(idUsuario, Usuario.class);
        if (u != null) {
            return true;
        } else {
            return false;
        }
    }

    //carregar usuário
    public Usuario getUsuario(long idUsuario) throws Exception {
        return (Usuario) usuarioDao.carregar(idUsuario, Usuario.class);
    }

}
