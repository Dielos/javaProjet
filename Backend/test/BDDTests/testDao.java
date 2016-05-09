/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDDTests;

import DAO.BoxTypeDao;
import DAO.CommandDao;
import DAO.CommandLineDao;
import DAO.DaoFactoryJpa;
import DAO.JpaBoxTypeDao;
import DAO.JpaCommandDao;
import DAO.JpaCommandLineDao;
import DAO.JpaTypeItemDao;
import DAO.TypeItemDao;
import Model.BoxType;
import Model.Command;
import Model.CommandLine;
import Model.TypeItem;

/**
 *
 * @author Robin
 */
public class testDao {
    public static void main(String[] args) {
        BoxTypeDao boxTypeManager = DaoFactoryJpa.getInstance(JpaBoxTypeDao.class);
        CommandDao commandManager = DaoFactoryJpa.getInstance(JpaCommandDao.class);
        TypeItemDao typeItemManager = DaoFactoryJpa.getInstance(JpaTypeItemDao.class);
        
        BoxType bt1 = new BoxType("b001", 42, 34, 1337);
        
        Command c1 = new Command("c001", 42, 33, 98);
        
        TypeItem ti1 = new TypeItem("i001", 42, 42, 42, 42, 42);
                
        boxTypeManager.create(bt1);
        
        commandManager.create(c1);
        
        typeItemManager.create(ti1);
        
        for (BoxType i : boxTypeManager.findAll()) {
            System.out.println(i);
        }
        
        for (Command i : commandManager.findAll()) {
            System.out.println(i);
            for (CommandLine j : i.getCommandLines())
                System.out.println(j);
        }
        
        for (TypeItem i : typeItemManager.findAll())
            System.out.println(i);
        
        
    }
}
