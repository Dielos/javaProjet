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
    public static boolean run() {
        Parser.main(null);
        Product product;
        ProductionLine pl;
        
        Instance instance = new Instance();
        
        instance.load();
        
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
                    //product.setBox(box);
                    instance.Products.add(product);
                    System.out.println("        "+product);
                    x++;
                }
            }
            o.setSendingDate(o.getSendingDate()+o.getStockMin());
            System.out.println(o);
        }
        
        return true;
    }
    
    public static void main(String[] args) {
        if (run()) System.out.println("oki");
        else System.out.println("nop");
    }
}
