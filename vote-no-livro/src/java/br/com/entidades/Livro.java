package br.com.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "LIVRO", schema = "APP"
)
public class Livro implements java.io.Serializable {

    private long idLivro;
    private String descricao;
    private String urlImg;

    public Livro() {
    }

    public Livro(long idLivro) {
        this.idLivro = idLivro;
    }

    public Livro(long idLivro, String descricao, String urlImg) {
        this.idLivro = idLivro;
        this.descricao = descricao;
        this.urlImg = urlImg;
    }

    @Id

    @Column(name = "ID_LIVRO", unique = true, nullable = false, precision = 10, scale = 0)
    @GeneratedValue(strategy = GenerationType.TABLE)
    public long getIdLivro() {
        return this.idLivro;
    }

    public void setIdLivro(long idLivro) {
        this.idLivro = idLivro;
    }

    @Column(name = "DESCRICAO", length = 100)
    public String getDescricao() {
        return this.descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    @Column(name = "URL_IMG", length = 100)
    public String getUrlImg() {
        return this.urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

}
