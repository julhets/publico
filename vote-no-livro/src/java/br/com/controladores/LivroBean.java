package br.com.controladores;

import br.com.daos.AvaliacaoDAO;
import br.com.daos.LivroDAO;
import br.com.entidades.Avaliacao;
import br.com.entidades.Livro;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Júlio
 */
public class LivroBean {

    private LivroDAO livroDao;

    public LivroBean() {
        livroDao = new LivroDAO();
    }

    //os livros são selecionados no BD de forma randômica e uma loop para geração de par de livros ainda não avaliados é executado;
    public List<Livro> getParLivrosAvaliacao(long idUsuario) throws Exception {
        List<Livro> listaLivros = livroDao.getLivros();
        List<Livro> parLivrosAvaliacao = new ArrayList();

        loop:
        for (Livro livro1 : listaLivros) {
            for (Livro livro2 : listaLivros) {
                if ((livro1.getIdLivro() != livro2.getIdLivro()) && !foiAvaliado(idUsuario, livro1.getIdLivro(), livro2.getIdLivro())) {
                    parLivrosAvaliacao.add(livro1);
                    parLivrosAvaliacao.add(livro2);
                    break loop;
                }
            }
        }
        return parLivrosAvaliacao;
    }

    //verifica se o par de livros já foi avaliado pelo usuário
    private boolean foiAvaliado(long idUsuario, long idLivro1, long idLivro2) {
        AvaliacaoDAO avaliacaoDao = new AvaliacaoDAO();
        List<Avaliacao> listaLivrosAvaliados = avaliacaoDao.listarAvaliacoes(idUsuario);
        for (Avaliacao a : listaLivrosAvaliados) {
            if ((idLivro1 == a.getLivro1().getIdLivro() || idLivro1 == a.getLivro2().getIdLivro()) && (a.getLivro1().getIdLivro() == idLivro2 || a.getLivro2().getIdLivro() == idLivro2)) {
                return true;
            }
        }
        return false;
    }

    public Livro getLivro(long idLivro) throws Exception {
        return (Livro) livroDao.carregar(idLivro, Livro.class);
    }

    /**
     * @return the livroDao
     */
    public LivroDAO getLivroDao() {
        return livroDao;
    }

    /**
     * @param livroDao the livroDao to set
     */
    public void setLivroDao(LivroDAO livroDao) {
        this.livroDao = livroDao;
    }

    public void povoarBaseDeLivros() throws Exception {
        //if (true) {
        //throw new Exception();
        //}

        Livro livro;
        if (livroDao.getLivros().isEmpty()) {
            livro = new Livro();
            livro.setDescricao("Anjos e demônios - Dan Brown");
            livro.setUrlImg("http://bimg2.mlstatic.com/livro-anjos-e-demonios-dan-brown-sebo-do-joao_MLB-F-4474512895_062013.jpg");
            livroDao.gravar(livro);

            livro = new Livro();
            livro.setDescricao("Fortaleza Digital - Dan Brown");
            livro.setUrlImg("http://skoob.s3.amazonaws.com/livros/126/FORTALEZA_DIGITAL_1228172272P.jpg");
            livroDao.gravar(livro);

            livro = new Livro();
            livro.setDescricao("A cabana - William P. Young");
            livro.setUrlImg("http://4.bp.blogspot.com/-Eu_gOpK6UtY/UY2Tmos5p4I/AAAAAAAAJ-Q/M4Q3CwL1iUs/s1600/A+cabana+(1).jpg");
            livroDao.gravar(livro);

            livro = new Livro();
            livro.setDescricao("A culpa é das estrelas - John Green");
            livro.setUrlImg("http://images1.folha.com.br/livraria/images/4/6/1183195-250x250.png");
            livroDao.gravar(livro);

            livro = new Livro();
            livro.setDescricao("A menina que roubava livros - Markus Zusak");
            livro.setUrlImg("http://cartacampinas.com.br/wp-content/uploads/a_menina_que_roubava_livros.jpg");
            livroDao.gravar(livro);
        }
    }

}
