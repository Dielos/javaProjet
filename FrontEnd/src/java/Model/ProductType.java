/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

/**
 *
 * @author Robin
 */
@Entity
public class ProductType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    // from file
    private String productName;
    private int setupTime;
    private int prodTime;
    private int height;
    private int width;
    private int empileMax;

    // links
    @OneToMany(cascade={CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name="NPRODUCTTYPE")
    private List<Product> products;
    
    @OneToOne(cascade={CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name="NPRODUCTIONLINE")
    private ProductionLine currentProductionLine;
    
    // other
    
    // method
    
    private void init() {
        products = new ArrayList<Product>();
    }

    public ProductType() {
        init();
    }

    public ProductType(String productName, int setupTime, int prodTime, int height, int width, int empileMax) {
        init();
        
        this.productName = productName;
        this.setupTime = setupTime;
        this.prodTime = prodTime;
        this.height = height;
        this.width = width;
        this.empileMax = empileMax;
    }
    
    public int getEmpileMax(BoxType bt) {
        int tmp = this.getHeight()*this.empileMax;
        if (tmp>bt.getHeight()) {
            tmp = (int)bt.getHeight()/this.getHeight();
            return tmp;
        }
        else
            return empileMax;
    }

    public boolean removeProduct(Product obj) {
        return products.remove(obj);
    }

    public boolean addProduct(Product obj) {
        if (obj.getTypeProduct() != null)
            return false;
        
        products.add(obj);
        obj.setTypeProduct(this);
        return true;
    }

    public int getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }

    public int getSetupTime() {
        return setupTime;
    }

    public int getProdTime() {
        return prodTime;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getEmpileMax() {
        return empileMax;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setSetupTime(int setupTime) {
        this.setupTime = setupTime;
    }

    public void setProdTime(int prodTime) {
        this.prodTime = prodTime;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setEmpileMax(int empileMax) {
        this.empileMax = empileMax;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + Objects.hashCode(this.productName);
        hash = 53 * hash + this.setupTime;
        hash = 53 * hash + this.prodTime;
        hash = 53 * hash + this.height;
        hash = 53 * hash + this.width;
        hash = 53 * hash + this.empileMax;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final ProductType other = (ProductType) obj;
        if (!Objects.equals(this.productName, other.productName)) {
            return false;
        }
        if (this.setupTime != other.setupTime) {
            return false;
        }
        if (this.prodTime != other.prodTime) {
            return false;
        }
        if (this.height != other.height) {
            return false;
        }
        if (this.width != other.width) {
            return false;
        }
        if (this.empileMax != other.empileMax) {
            return false;
        }
        return true;
    }

    

    

    @Override
    public String toString() {
        return "TypeProduct{" + "id=" + id + ", setupTime=" + setupTime + ", prodTime=" + prodTime + ", height=" + height + ", width=" + width + ", empileMax=" + empileMax + '}';
    }
    
    
}
