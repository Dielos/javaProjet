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
import Model.BoxType;
import Model.Instance;
import Model.Order;
import Model.OrderLine;
import Model.Product;
import Model.ProductionLine;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Robin
 */
public class Scheduler {
    public static boolean run(Instance instance) {
        
        Product product;
        //ProductionLine pl;
        
        //InstanceDao instanceManager = DaoFactoryJpa.getInstance(JpaInstanceDao.class);
        //Instance instance = (Instance)(instanceManager.findAll().toArray()[0]);
        instance.sortOrders();
        //Instance instance = new Instance();
        
        //instance.load();
        
        int O=0;
        Order o;
        List<Order> os = instance.getOrders();
        while (O!=os.size()) {
            o = os.get(O);
            O++;
        //for (Order o : instance.getOrders()) {
            System.out.println(o);
            
            ArrayList<OrderLine> ols = new ArrayList(o.getSortedOrderLines());
            
            int x= 0;
            while (x!=ols.size()) {
                for (ProductionLine pl : instance.getProductionLines()) {
                    if (ols.get(x).getTypeProduct() == pl.getProduct()) {
                        System.out.println("*** CONCCAATTTT ***");
                        int y=0;
                        while (y!=ols.get(x).getQuantity()) {
                            product = new Product();
                            product.setOrderLine(ols.get(x));
                            product.setProductionLine(pl);
                            Box b = o.getBoxForItem(ols.get(x).getTypeProduct());
                            product.setBox(b);
                            b.addProduct(product);
                            instance.getProducts().add(product);
                            System.out.println("        "+product);
                            y++;
                        }
                        
                        ols.remove(x);
                        x--;
                        break;
                    }
                }
                x+=1;
            }
            //351258
            
            ArrayList<OrderLine> atEnd = new ArrayList<OrderLine>();
            if (O!=os.size()) {
                Order o2 = os.get(O);
                x=0;
                while (x!=ols.size()) {
                    for (OrderLine ol2 : o2.getOrderLines()) {
                        if (ol2.getTypeProduct()==ols.get(x).getTypeProduct()) {
                            atEnd.add(ols.get(x));
                            ols.remove(x);
                            x--;
                            break;
                        }
                    }
                    x++;
                }
            }
            
            
            for (OrderLine ol : ols) {
                System.out.println("    "+ol);
                ProductionLine pl = instance.getFirstLineAvailable();
                x = 0;
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
            
            for (OrderLine ol : atEnd) {
                System.out.println("    "+ol);
                ProductionLine pl = instance.getFirstLineAvailable();
                x = 0;
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
        
        for (BoxType bt : instance.getBoxTypes()) {
            System.out.println(""+bt.getBoxs().size());
        }
        
        InstanceDao instanceManager = DaoFactoryJpa.getInstance(JpaInstanceDao.class);
        instanceManager.create(instance);
        
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
