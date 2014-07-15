package br.com.daos;

import br.com.bd.Path;
import br.com.interfaces.IDAO;
import br.com.util.HibernateUtil;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

public abstract class GenericDAO implements IDAO {

    //Construtor   
    public GenericDAO() {
    }

    //Insert
    @Override
    public void gravar(Object objeto) throws Exception {

        try {

            getSession().save(objeto);
            getSession().flush();

        } catch (HibernateException he) {
            System.out.println(
                    "Generic Persistence -> Erro na inserção :" + he.getMessage());
            throw he;
        }
    }

    //getList  
    @Override
    public java.util.List listar(String query) throws Exception {

        Query q1 = getSession().createQuery(query);
        List objeto = q1.list();

        return objeto;

    }

    //Retrieve  
    @Override
    public Object carregar(Long pk, Class classe) throws Exception {

        Object objeto = null;

        try {
            objeto = (Object) getSession().get(classe, pk);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            return objeto;
        }
    }

    //Update  
    @Override
    public void alterar(Object objeto) throws Exception {

        try {

            getSession().update(objeto);
            getSession().flush();

        } catch (HibernateException he) {
            System.out.println(
                    "Generic Persistence -> Erro na alteração: " + he.getMessage());
            throw he;
        }
    }

    //Delete
    @Override
    public void remover(Object objeto) throws Exception {

        try {

            getSession().delete(objeto);
            getSession().flush();

        } catch (HibernateException he) {
            System.out.println(
                    "Generic Persistence -> Erro na exclusão: " + he.getMessage());
            throw he;
        }
    }

    public Session getSession() {
        return HibernateUtil.getSession();
    }

    public String retornaSql(String sqlName) throws JDOMException, IOException {
        Path p = new Path();

        File f = new File(p.getApplicationPath() + System.getProperty("file.separator") + "database.xml");

        //File f = new File("/database.xml");

        //Criamos uma classe SAXBuilder que vai processar o XML4
        SAXBuilder sb = new SAXBuilder();

        //Este documento agora possui toda a estrutura do arquivo.
        Document d = sb.build(f);

        //Recuperamos o elemento root
        Element mural = d.getRootElement();

        //Recuperamos os elementos filhos (children)
        List elements = mural.getChildren();
        Iterator i = elements.iterator();

        //Iteramos com os elementos filhos, e filhos do dos filhos
        while (i.hasNext()) {
            Element element = (Element) i.next();
            if (element.getAttributeValue("id").equals(sqlName)) {
                return element.getText();
            }
        }
        return "";
    }
    
    public void rollBack() {
        getSession().getTransaction().rollback();
        //dou o rollback más re-abro a conexão para caso haja algum outro procedimento que necessite da mesma. Além disso, evito erro no filtro do hibernate.
        getSession().beginTransaction();
    }
}