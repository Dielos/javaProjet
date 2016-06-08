/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

import static DAO.JpaDao.em;
import Model.Order;
import java.util.List;
import javax.persistence.Query;

/**
 *
 * @author Robin
 */
public class JpaOrderDao extends JpaDao<Order> implements OrderDao{

    @Override
    public List<Order> findAllChrono() {
        final String strQuery = "SELECT obj FROM " + kek.getSimpleName() + " obj ORDER BY obj.dateLimit";
        Query query = em.createQuery(strQuery);
        return query.getResultList(); 
    }
    
}
