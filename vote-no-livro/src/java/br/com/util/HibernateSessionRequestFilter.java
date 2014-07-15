/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.util;

import java.io.IOException;
import javax.servlet.*;
import org.hibernate.SessionFactory;
import org.hibernate.StaleObjectStateException;

//classe que intermedia as requisições/respostas entre cliente e servidor, abrindo, comitando e fechando as transações realizadas pelo hibernate;
public class HibernateSessionRequestFilter implements Filter {

    private SessionFactory sf;

    public void doFilter(ServletRequest request,
            ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {

        try {
            sf.getCurrentSession().beginTransaction();
            chain.doFilter(request, response);
            sf.getCurrentSession().getTransaction().commit();

        } catch (StaleObjectStateException staleEx) {
            throw staleEx;
        } catch (Throwable ex) {
            ex.printStackTrace();
            try {
                if (sf.getCurrentSession().getTransaction().isActive()) {
                    sf.getCurrentSession().getTransaction().rollback();
                }
            } catch (Throwable rbEx) {
            }
            throw new ServletException(ex);
        }
    }

    public void init(FilterConfig filterConfig) throws ServletException {
        sf = HibernateUtil.getSessionFactory();
    }

    public void destroy() {
    }
}
