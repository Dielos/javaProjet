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
import java.io.Serializable;
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
public class Instance implements Serializable {
    //private static final long serialVersionUID = 42L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    
    private String instanceName;
    
    @OneToMany(cascade={CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name="NBOXTYPES")
    private List<BoxType> boxTypes;
    
    @OneToMany(cascade={CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name="NORDERS")
    private List<Order> orders;
    
    @OneToMany(cascade={CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name="NPRODUCTTYPES")
    private List<ProductType> productTypes;
    
    @OneToMany(cascade={CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name="NPRODUCTIONLINES")
    private List<ProductionLine> productionLines;
    
    @OneToMany(cascade={CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name="NPRODUCTS")
    private List<Product> products;
    
    public ProductionLine getFirstLineAvailable() {
        ProductionLine plret=null;
        System.out.println("-----"+productionLines.size());
        for (ProductionLine pl : productionLines) {
            if (plret == null || pl.getDateAvailable() < plret.getDateAvailable()) {
                plret = pl;
            }
        }
        return plret;
    }
    
    
    
    private void init() {
        boxTypes = new ArrayList<>();
        orders = new ArrayList<>();
        products = new ArrayList<>();
        productionLines = new ArrayList<>();
        productTypes = new ArrayList<>();
    }
    
    public Instance () {
        init();
    }
    
    public Instance (String instanceName) {
        init();
        this.instanceName = instanceName;
    }
    
    public int getTotalSetUpTime() {
        int sum=0;
        for (Product p : getSortedProducts()) {
            sum += p.getProductionLine().setProductType(p.getTypeProduct());
        }
        
        return sum;
    }
    
    public int getTotalProductionTime() {
        int sum = 0;
        for (Order o : orders) {
            for (OrderLine ol : o.getOrderLines()) {
                sum += ol.getQuantity() * ol.getTypeProduct().getProdTime();
            }
        }
        return sum;
    }
    
    public void addOrder(Order o) {
        this.orders.add(o);
        o.setInstance(this);
    }
    
    public int getOrderCost() {
        int sumOrder=0;
        for (Order o: orders) {
            sumOrder += o.getPenalityCost();
        }
        return sumOrder;
    }
    
    public Order getOrderById(int id) {
        for (Order o : orders) {
            if (o.getId()==id)
                return o;
        }
        return null;
    }
    
    public int getBoxCost() {
        int sumBox=0;
        for (BoxType b: boxTypes) {
            sumBox += b.getTotalBoxesCost();
        }
        return sumBox;
    }
    
    public int getTotalCost() {
        return getOrderCost()+getBoxCost();
    }
    
    public void sortOrders() {
        Collections.sort(orders, new Comparator<Order>() {
                                                        @Override
                                                        public int compare(Order o1, Order o2){
                                                            return Integer.compare(o1.getDateLimit()-o1.getStockMin(), o2.getDateLimit()-o2.getStockMin());
                                                        }
        });
    }
    
    public void sortOrdersByName() {
        Collections.sort(orders, new Comparator<Order>() {
                                                        @Override
                                                        public int compare(Order o1, Order o2){
                                                            return o1.getOrderName().compareTo(o2.getOrderName());
                                                        }
        });
    }
    
    public void sortProducts() {
        Collections.sort(products, new Comparator<Product>() {
            @Override
            public int compare(Product o1, Product o2) {
                return Integer.compare(o1.getDateStart(), o2.getDateStart());
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


    public int getId() {
        return id;
    }

    public String getInstanceName() {
        return instanceName;
    }


    public List<BoxType> getBoxTypes() {
        return boxTypes;
    }


    public List<Order> getOrders() {
        return orders;
    }

    public List<ProductType> getProductTypes() {
        return productTypes;
    }

    public List<ProductionLine> getProductionLines() {
        return productionLines;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setInstanceName(String instanceName) {
        this.instanceName = instanceName;
    }

    public void setBoxTypes(List<BoxType> boxTypes) {
        this.boxTypes = boxTypes;
    }

    public void setOrders(List<Order> orders) {
        this.orders = orders;
    }

    public void setProductionLines(List<ProductionLine> productionLines) {
        this.productionLines = productionLines;
    }

    public List<Product> getProducts() {
        return products;
    }
    
    public List<Product> getSortedProducts() {
        sortProducts();
        return products;
    }
}
