/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package backend;

import DAO.BoxTypeDao;
import DAO.DaoFactoryJpa;
import DAO.JpaBoxTypeDao;
import DAO.JpaOrderDao;
import DAO.JpaProductDao;
import DAO.OrderDao;
import DAO.ProductDao;
import Model.BoxType;
import Model.Order;
import Model.Product;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.Collection;

/**
 *
 * @author Mélody
 */
public class ReverseParser {
    
    
    public void createFile(){
        try { 
            java.io.File fichier = new java.io.File("fichierSoltuionTest.sol"); 
            fichier.createNewFile(); // Cette fonction doit être appelée au sein d'un bloc TRY 
        } catch (IOException e) { 
            System.out.println("Impossible de créer le fichier"); 
        } 
    }
    
    public String getTypeBoxInfos(){
        //getTypeBoxInfos
        BoxTypeDao boxTypeManager = DaoFactoryJpa.getInstance(JpaBoxTypeDao.class);
        Collection<BoxType> tabBT = boxTypeManager.findAll();
        String sol="";
        for(BoxType bt: tabBT){
            sol = sol+"\n"+bt.getBoxName()+" "+bt.getBoxs().size();
        }
        sol=sol+"\n";
        
        OrderDao orderManager = DaoFactoryJpa.getInstance(JpaOrderDao.class);
        Collection<Order> tabOrder = orderManager.findAll();
        for(Order o: tabOrder){
            sol = sol+"\n"+o.getOrderName()+" "+o.getSendingDate();
        }
        sol=sol+"\n";
        
        ProductDao productManager = DaoFactoryJpa.getInstance(JpaProductDao.class);
        Collection<Product> tabProduct = productManager.findAll();
        for(Product p: tabProduct){
            sol = sol+"\n"+p.getOrderLine().getOrder().getOrderName()+" "+
                    p.getProductionLine().getNum()+" "+p.getDateStart()+" "+
                    p.getBox().getBoxType().getBoxName()+" "+p.getBox().getNum();
        }
        sol=sol+"\n";
        
        System.out.println(sol);
        return sol;
    }

    
    public void writeInFile() throws FileNotFoundException, UnsupportedEncodingException{
        PrintWriter writer = new PrintWriter("fichierSoltuionTest.sol", "UTF-8");
        writer.println("The first line");
        writer.println("The second line");
        writer.close();
    }
    
    public static void main(String[] args) throws FileNotFoundException, UnsupportedEncodingException {
        ReverseParser test = new ReverseParser();
        test.getTypeBoxInfos();
       // test.createFile();
       // test.writeInFile();
       
    }
}
