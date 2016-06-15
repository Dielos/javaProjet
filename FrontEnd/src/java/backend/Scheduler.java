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
import Model.Box;
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
        
        for (Order o : instance.getOrders()) {
            System.out.println(o);
            
            for (OrderLine ol : o.getOrderLines()) {
                System.out.println("    "+ol);
                pl = instance.getFirstLineAvailable();
                int x = 0;
                while (x!=ol.getQuantity()) {
                    product = new Product();
                    product.setOrderLine(ol);
                    product.setProductionLine(pl);
                    Box b = o.getBoxForItem(ol.getTypeProduct());
                    product.setBox(b);
                    b.addProduct(product);
                    instance.getProducts().add(product);
                    System.out.println("        "+product);
                    x++;
                }
            }
            o.setSendingDate(o.getSendingDate()+o.getStockMin());
            o.freeBoxes();
            System.out.println(o);
        }
        
        System.out.println("Order cost : "+instance.getOrderCost());
        System.out.println("Boxes cost : "+instance.getBoxCost());
        System.out.println("Total cost : "+instance.getTotalCost());
        
        System.out.println("\nTotal production time : "+instance.getTotalProductionTime());
        System.out.println("Total setUp time : "+instance.getTotalSetUpTime());
        System.out.println("NProdLines : "+instance.getProductionLines().size());
        
        /*for (Order o : instance.getOrders()) {
            System.out.println(""+o.getBoxs());
        }*/
        
        InstanceDao instanceManager = DaoFactoryJpa.getInstance(JpaInstanceDao.class);
        instanceManager.update(instance);
        
        /*System.out.println("\n\n-----------------\n\n");
        
        for (Order o : instance.getOrders()) {
            System.out.println(""+o.getBoxs());
        }*/
        
        return true;
    }
    
    public static void main(String[] args) {
        Parser.main(null);
        
        InstanceDao instanceManager = DaoFactoryJpa.getInstance(JpaInstanceDao.class);
        Instance instance = instanceManager.getInstanceByName("FileName1");//(Instance)(instanceManager.findAll().toArray()[0]);
        
      //if (run(instance)) System.out.println("oki");
      //else System.out.println("nop");
    } //Order{id=23, stockMin=13, dateLimit=4346, penality=9.45, startProductionDate=5809, sendingDate=6056}
}
