/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import static DAO.JpaDao.em;
import Model.Instance;
import java.io.Serializable;
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
        return (Instance)query.getSingleResult();   
    }
    
}
