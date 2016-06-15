/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BDDTests;

import DAO.DaoFactoryJpa;
import DAO.InstanceDao;
import DAO.JpaInstanceDao;
import Model.Instance;
import Model.Product;

/**
 *
 * @author Dielos
 */
public class test {
    public static void main(String[] args) {
        
        InstanceDao instanceManager = DaoFactoryJpa.getInstance(JpaInstanceDao.class);
        Instance instance = instanceManager.getInstanceByName("FileName1");
        for (Product p : instance.getSortedProducts()){
            System.out.println(p);
        }
    }
}
