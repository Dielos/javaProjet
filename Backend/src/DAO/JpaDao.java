/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package DAO;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Robin
 * @param <T>
 * Implementation of interface Dao for Jpa
 */
public abstract class JpaDao<T> implements Dao<T>{
    static protected EntityManagerFactory emf = Persistence.createEntityManagerFactory("BackendPU");
    static protected EntityManager em = emf.createEntityManager();
    static protected EntityTransaction et = em.getTransaction();
    protected Class<T> kek;
    
    //public abstract JpaDao getInstance ();
    
    protected JpaDao () {
        //emf = Persistence.createEntityManagerFactory("BackendPU");
        //em = emf.createEntityManager();
        //et = em.getTransaction();
        
        //May look like quite tricky, this line simply get the class of parameter "T"
        kek = (Class<T>) ((ParameterizedType)getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }
    
    public boolean create(T obj) {
        et.begin();
        em.persist(obj);
        et.commit();
        return true;
    }
    
    public T findId (Integer id) {
        return em.find(kek, id);
    }
    
    public Collection<T> findAll() {
        final String strQuery = "SELECT obj FROM " + kek.getSimpleName() + " obj";
        Query query = em.createQuery(strQuery);
        return query.getResultList();      
    }
    
    public boolean update (T obj) {
        et.begin();
        em.merge(obj);
        et.commit();
        return true;
    }
    
    public boolean delete (T obj) {
        em.remove(obj);
        return true;
    }
    
    public boolean deleteAll() {
        final String strQuery = "DELETE FROM " + kek.getSimpleName();
        Query query = em.createQuery(strQuery);
        et.begin();
        boolean ret = query.executeUpdate()>1;
        et.commit();
        return ret;
    }
    
    public void close() {
        em.close();
        emf.close();
    }
}
