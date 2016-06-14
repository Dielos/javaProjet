/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package backend;

import DAO.DaoFactoryJpa;
import DAO.InstanceDao;
import DAO.JpaInstanceDao;
import DAO.JpaOrderDao;
import DAO.JpaProductionLineDao;
import DAO.OrderDao;
import DAO.ProductionLineDao;
import Model.Instance;
import Model.Order;
import Model.OrderLine;
import Model.Product;
import Model.ProductionLine;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Robin
 */
public class Scheduler {
    public static boolean run(Instance instance) {
        
        Product product;
        ProductionLine pl;
        
        //InstanceDao instanceManager = DaoFactoryJpa.getInstance(JpaInstanceDao.class);
        //Instance instance = (Instance)(instanceManager.findAll().toArray()[0]);
        instance.sortOrders();
        //Instance instance = new Instance();
        
        //instance.load();
        
        for (Order o : instance.orders) {
            System.out.println(o);
            
            for (OrderLine ol : o.getOrderLines()) {
                System.out.println("    "+ol);
                pl = instance.getFirstLineAvailable();
                int x = 0;
                while (x!=ol.getQuantity()) {
                    product = new Product();
                    product.setOrderLine(ol);
                    product.setProductionLine(pl);
                    product.setBox(o.getBoxForItem(ol.getTypeProduct()));
                    instance.Products.add(product);
                    System.out.println("        "+product);
                    x++;
                }
            }
            o.setSendingDate(o.getSendingDate()+o.getStockMin());
            System.out.println(o);
        }
        
        System.out.println("Total cost : "+instance.getTotalCost());
        
        return true;
    }
    
    public static void main(String[] args) {
        Parser.main(null);
        
        InstanceDao instanceManager = DaoFactoryJpa.getInstance(JpaInstanceDao.class);
        Instance instance = instanceManager.getInstanceByName("FileName1");//(Instance)(instanceManager.findAll().toArray()[0]);
        
        if (run(instance)) System.out.println("oki");
        else System.out.println("nop");
    } //Order{id=23, stockMin=13, dateLimit=4346, penality=9.45, startProductionDate=5809, sendingDate=6056}
}
