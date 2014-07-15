package br.com.entidades;

public class Resultado {

    private Livro livro;
    private int votos;

    public Resultado() {

    }

    public Resultado(Livro livro, int votos) {
        this.livro = livro;
        this.votos = votos;
    }

    /**
     * @return the livro
     */
    public Livro getLivro() {
        return livro;
    }

    /**
     * @param livro the livro to set
     */
    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    /**
     * @return the votos
     */
    public int getVotos() {
        return votos;
    }

    /**
     * @param votos the votos to set
     */
    public void setVotos(int votos) {
        this.votos = votos;
    }
}
