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
import DAO.JpaProductionLineDao;
import Model.BoxType;
import Model.Order;
import Model.OrderLine;
import Model.ProductType;
import DAO.OrderDao;
import DAO.OrderLineDao;
import DAO.ProductTypeDao;
import DAO.ProductionLineDao;
import Model.Box;
import Model.Product;
import Model.ProductionLine;

/**
 *
 * @author Robin
 */
public class testDao {
    public static void main(String[] args) {
        BoxTypeDao boxTypeManager = DaoFactoryJpa.getInstance(JpaBoxTypeDao.class);
        OrderDao orderManager = DaoFactoryJpa.getInstance(JpaOrderDao.class);
        ProductTypeDao productItemManager = DaoFactoryJpa.getInstance(JpaProductTypeDao.class);
        //orderLine
        //Box
        ProductionLineDao productionLineManager = DaoFactoryJpa.getInstance(JpaProductionLineDao.class);
        //product
        
        // ----- //
        BoxType bt1 = new BoxType("b001", 42, 34, 1337);
        
        Order c1 = new Order("c001", 42, 33, 98);
        
        ProductType ti1 = new ProductType("i001", 42, 42, 42, 42, 42);
        
        ti1.addProduct(new Product(42));
        
        c1.addOrderLine(new OrderLine(42, ti1));
        
        bt1.addBox(new Box(1));
        
        ProductionLine pl1 = new ProductionLine(1, 0);
        
        // ----- //
        /*productItemManager.create(ti1);
        
        orderManager.create(c1);
        
        boxTypeManager.create(bt1);
        
        productionLineManager.create(pl1);*/
        
        // ----- //
        for (BoxType i : boxTypeManager.findAll()) {
            System.out.println(i);
            for (Box j : i.getBoxs())
                System.out.println(j);
        }
        
        for (Order i : orderManager.findAll()) {
            System.out.println(i);
            for (OrderLine j : i.getOrderLines())
                System.out.println(j);
        }
        System.out.println("kek");
        for (ProductType i : productItemManager.findAll()) {
            System.out.println(i);
            for (Product j : i.getProducts())
                System.out.println(j);
        }
        
        System.out.println("ze");
        System.out.println(productItemManager.findId(1));
        
        for (ProductionLine i : productionLineManager.findAll())
            System.out.println(i);
    }
}
