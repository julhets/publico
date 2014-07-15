package br.com.entidades;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "USUARIO", schema = "APP"
)
public class Usuario implements java.io.Serializable {

    private long idUsuario;
    private String email;
    private String nome;

    public Usuario() {
    }

    public Usuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuario(long idUsuario, String email, String nome) {
        this.idUsuario = idUsuario;
        this.email = email;
        this.nome = nome;
    }

    @Id
    @Column(name = "ID_USUARIO", unique = true, nullable = false, precision = 10, scale = 0)
    @GeneratedValue(strategy = GenerationType.TABLE)
    public long getIdUsuario() {
        return this.idUsuario;
    }

    public void setIdUsuario(long idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Column(name = "EMAIL", length = 100)
    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "NOME", length = 100)
    public String getNome() {
        return this.nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

}
