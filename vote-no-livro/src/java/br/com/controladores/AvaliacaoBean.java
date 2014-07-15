/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.controladores;

import br.com.daos.AvaliacaoDAO;
import br.com.entidades.Avaliacao;

/**
 *
 * @author Júlio
 */
public class AvaliacaoBean {

    private Avaliacao avaliacao;
    private AvaliacaoDAO avaliacaoDao;

    public AvaliacaoBean() {
        avaliacao = new Avaliacao();
        avaliacaoDao = new AvaliacaoDAO();
    }

    public void gravarAvaliacao() throws Exception {
        avaliacaoDao.gravar(avaliacao);
    }

    /**
     * @return the avaliacao
     */
    public Avaliacao getAvaliacao() {
        return avaliacao;
    }

    /**
     * @param avaliacao the avaliacao to set
     */
    public void setAvaliacao(Avaliacao avaliacao) {
        this.avaliacao = avaliacao;
    }

    /**
     * @return the avaliacaoDao
     */
    public AvaliacaoDAO getAvaliacaoDao() {
        return avaliacaoDao;
    }

    /**
     * @param avaliacaoDao the avaliacaoDao to set
     */
    public void setAvaliacaoDao(AvaliacaoDAO avaliacaoDao) {
        this.avaliacaoDao = avaliacaoDao;
    }
}
