/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import DAO.BoxTypeDao;
import DAO.DaoFactoryJpa;
import DAO.JpaBoxTypeDao;
import DAO.JpaOrderDao;
import DAO.JpaProductTypeDao;
import DAO.JpaProductionLineDao;
import DAO.OrderDao;
import DAO.ProductTypeDao;
import DAO.ProductionLineDao;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 *
 * @author Robin
 */
public class Instance {
    public Collection<BoxType> BoxTypes;
    public Collection<Order> orders;
    public Collection<ProductType> products;
    public Collection<ProductionLine> productionLines;
    
    public Collection<Product> Products;
    
    public ProductionLine getFirstLineAvailable() {
        ProductionLine plret=null;
        for (ProductionLine pl : productionLines) {
            if (plret == null || pl.getDateAvailable() < plret.getDateAvailable())
                plret = pl;
        }
        return plret;
    }
    
    
    
    private void init() {
        /*BoxTypes = new ArrayList<>();
        orders = new ArrayList<>();
        products = new ArrayList<>();
        productionLines = new ArrayList<>();*/
        Products = new ArrayList<>();
    }
    
    public Instance () {
        init();
    }
    
    public void load() {
        ProductTypeDao ProductTypeManager = DaoFactoryJpa.getInstance(JpaProductTypeDao.class);
        OrderDao orderManager = DaoFactoryJpa.getInstance(JpaOrderDao.class);
        BoxTypeDao boxTypeManager = DaoFactoryJpa.getInstance(JpaBoxTypeDao.class);
        ProductionLineDao productionLineManager = DaoFactoryJpa.getInstance(JpaProductionLineDao.class);
        
        BoxTypes = boxTypeManager.findAll();
        orders = orderManager.findAllChrono();
        products = ProductTypeManager.findAll();
        productionLines = productionLineManager.findAll();
    }
    
}
