package br.com.entidades;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "AVALIACAO", schema = "APP"
)
public class Avaliacao implements java.io.Serializable {

    private long idAvaliacao;
    private Usuario usuario;
    private Livro livro1;
    private Livro livro2;
    //1 ou 2 - depende de qual dos dois livros o usuário escolheu
    private int avaliacao;

    public Avaliacao() {
        usuario = new Usuario();
        livro1 = new Livro();
        livro2 = new Livro();
    }

    public Avaliacao(long idAvaliacao, Usuario usuario, Livro livro1, Livro livro2) {
        this.idAvaliacao = idAvaliacao;
        this.usuario = usuario;
        this.livro1 = livro1;
        this.livro2 = livro2;
    }

    @Id
    @Column(name = "ID_AVALIACAO", unique = true, nullable = false, precision = 10, scale = 0)
    @GeneratedValue(strategy = GenerationType.TABLE)
    public long getIdAvaliacao() {
        return this.idAvaliacao;
    }

    public void setIdAvaliacao(long idAvaliacao) {
        this.idAvaliacao = idAvaliacao;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_USUARIO")
    public Usuario getUsuario() {
        return this.usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_LIVRO1")
    public Livro getLivro1() {
        return this.livro1;
    }

    public void setLivro1(Livro livro1) {
        this.livro1 = livro1;
    }

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "ID_LIVRO2")
    public Livro getLivro2() {
        return this.livro2;
    }

    public void setLivro2(Livro livro2) {
        this.livro2 = livro2;
    }

    /**
     * @return the avaliacao
     */
    @Column(name = "avaliacao", nullable = false, precision = 1, scale = 0)
    public int getAvaliacao() {
        return avaliacao;
    }

    /**
     * @param avaliacao the avaliacao to set
     */
    public void setAvaliacao(int avaliacao) {
        this.avaliacao = avaliacao;
    }

}
