/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import static DAO.JpaDao.em;
import Model.Instance;
import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Query;

/**
 *
 * @author Robin
 */
public class JpaInstanceDao extends JpaDao<Instance> implements InstanceDao {

    @Override
    public Instance getInstanceByName(String name) {
        String strQuery = "SELECT obj FROM Instance obj WHERE obj.instanceName = :name";
        Query query = em.createQuery(strQuery);
        query.setParameter("name", name);
        List<Instance> var = query.getResultList();
        System.out.println(name);
        if(var.isEmpty()){
            System.out.println("empty");
            return null;
        }
        return var.get(0);   
    }
    
    @Override
    public boolean create(Instance obj) {
        et.begin();
        Instance tmp = getInstanceByName(obj.getInstanceName());
        if (tmp!=null)
            this.delete(tmp);
        em.persist(obj);
        et.commit();
        return true;
    }
    
}
