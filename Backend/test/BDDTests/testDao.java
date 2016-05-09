/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDDTests;

import DAO.BoxTypeDao;
import DAO.DaoFactoryJpa;
import DAO.JpaBoxTypeDao;
import DAO.JpaOrderDao;
import DAO.JpaOrderLineDao;
import DAO.JpaProductTypeDao;
import Model.BoxType;
import Model.Order;
import Model.OrderLine;
import Model.ProductType;
import DAO.OrderDao;
import DAO.OrderLineDao;
import DAO.ProductTypeDao;

/**
 *
 * @author Robin
 */
public class testDao {
    public static void main(String[] args) {
        BoxTypeDao boxTypeManager = DaoFactoryJpa.getInstance(JpaBoxTypeDao.class);
        OrderDao commandManager = DaoFactoryJpa.getInstance(JpaOrderDao.class);
        ProductTypeDao typeItemManager = DaoFactoryJpa.getInstance(JpaProductTypeDao.class);
        
        BoxType bt1 = new BoxType("b001", 42, 34, 1337);
        
        Order c1 = new Order("c001", 42, 33, 98);
        
        ProductType ti1 = new ProductType("i001", 42, 42, 42, 42, 42);
        
        c1.addOrderLine(new OrderLine(42, ti1));
                
        boxTypeManager.create(bt1);
        
        commandManager.create(c1);
        
        typeItemManager.create(ti1);
        
        for (BoxType i : boxTypeManager.findAll()) {
            System.out.println(i);
        }
        
        for (Order i : commandManager.findAll()) {
            System.out.println(i);
            for (OrderLine j : i.getOrderLines())
                System.out.println(j);
        }
        
        for (ProductType i : typeItemManager.findAll())
            System.out.println(i);
        
        
    }
}
