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
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

/**
 *
 * @author Robin
 */

@Entity
public class Instance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    
    public String instanceName;
    
    @OneToMany(cascade={CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name="NBOXTYPES")
    public List<BoxType> boxTypes;
    
    @OneToMany(cascade={CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name="NORDERS")
    public List<Order> orders;
    
    @OneToMany(cascade={CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name="NPRODUCTS")
    public List<ProductType> products;
    
    @OneToMany(cascade={CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name="NPRODUCTIONLINES")
    public List<ProductionLine> productionLines;
    
    @OneToMany(cascade={CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name="NPRODUCTS")
    public List<Product> Products;
    
    public ProductionLine getFirstLineAvailable() {
        ProductionLine plret=null;
        for (ProductionLine pl : productionLines) {
            if (plret == null || pl.getDateAvailable() < plret.getDateAvailable())
                plret = pl;
        }
        return plret;
    }
    
    
    
    private void init() {
        boxTypes = new ArrayList<>();
        orders = new ArrayList<>();
        products = new ArrayList<>();
        productionLines = new ArrayList<>();
        Products = new ArrayList<>();
    }
    
    public Instance () {
        init();
    }
    
    public Instance (String instanceName) {
        init();
        this.instanceName = instanceName;
    }
    
    public void sortOrders() {
        Collections.sort(orders, new Comparator<Order>() {
                                                        @Override
                                                        public int compare(Order o1, Order o2){
                                                            return Integer.compare(o1.getDateLimit(), o2.getDateLimit());
                                                        }
        });
    }
    
    public void load() {
        ProductTypeDao ProductTypeManager = DaoFactoryJpa.getInstance(JpaProductTypeDao.class);
        OrderDao orderManager = DaoFactoryJpa.getInstance(JpaOrderDao.class);
        BoxTypeDao boxTypeManager = DaoFactoryJpa.getInstance(JpaBoxTypeDao.class);
        ProductionLineDao productionLineManager = DaoFactoryJpa.getInstance(JpaProductionLineDao.class);
        
        boxTypes = new ArrayList(boxTypeManager.findAll());
        //orders = orderManager.findAllChrono();
        products = new ArrayList(ProductTypeManager.findAll());
        productionLines = new ArrayList(productionLineManager.findAll());
    }
    
}
