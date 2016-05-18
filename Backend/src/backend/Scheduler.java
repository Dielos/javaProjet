/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package backend;

import DAO.DaoFactoryJpa;
import DAO.JpaOrderDao;
import DAO.JpaProductionLineDao;
import DAO.OrderDao;
import DAO.ProductionLineDao;
import Model.Order;
import Model.OrderLine;
import Model.ProductionLine;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Robin
 */
public class Scheduler {
    public static boolean run() {
        ProductionLineDao productionLineManager = DaoFactoryJpa.getInstance(JpaProductionLineDao.class);
        OrderDao orderManager = DaoFactoryJpa.getInstance(JpaOrderDao.class);
        
        
        Collection<ProductionLine> plines = productionLineManager.findAll();
        if (plines.isEmpty()) return false;
        
        for (Order o : orderManager.findAllChrono())
        {
            System.out.println(o);
            for (OrderLine ol : o.getOrderLines())
                System.out.println(ol);
        }
        return true;
    }
    
    public static void main(String[] args) {
        if (run()) System.out.println("oki");
        else System.out.println("nop");
    }
}