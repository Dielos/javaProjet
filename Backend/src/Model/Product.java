/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package Model;

import java.util.ArrayList;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author Robin
 */
@Entity
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    // from file
    
    // links
    @ManyToOne(cascade={CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name="NORDERLINE")
    private OrderLine orderLine;
    
    @ManyToOne(cascade={CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name="NPRODUCTTYPE")
    private ProductType typeProduct;
    
    @ManyToOne(cascade={CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name="NBOX")
    private Box box;
    
    @ManyToOne(cascade={CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name="NORDER")
    private ProductionLine productionLine;
    
    @OneToOne(cascade={CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name="NPRODUCTIONLINE")
    private Product product;
    
    // other
    private int DateStart;
    
    // method
    
    private void init() {
        orderLine = null;
        typeProduct = null;
        box = null;
        productionLine = null;
        product = null;
    }

    public Product(int DateStart) {
        init();
        
        this.DateStart = DateStart;
    }

    public Product() {
        init();
    }

    public int getId() {
        return id;
    }

    public OrderLine getOrderLine() {
        return orderLine;
    }

    public ProductType getTypeProduct() {
        return typeProduct;
    }

    public Box getBox() {
        return box;
    }

    public ProductionLine getProductionLine() {
        return productionLine;
    }

    public Product getProduct() {
        return product;
    }

    public int getDateStart() {
        return DateStart;
    }

    public void setOrderLine(OrderLine orderLine) {
        this.orderLine = orderLine;
        this.typeProduct = orderLine.getTypeProduct();
    }

    public void setTypeProduct(ProductType typeProduct) {
        this.typeProduct = typeProduct;
    }

    public void setBox(Box box) {
        this.box = box;
    }

    public void setProductionLine(ProductionLine productionLine) {
        this.productionLine = productionLine;
        this.DateStart = productionLine.produce(typeProduct);
        if (orderLine.getOrder().getSendingDate() < DateStart)
            orderLine.getOrder().setSendingDate(DateStart);
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public void setDateStart(int DateStart) {
        this.DateStart = DateStart;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + this.id;
        hash = 17 * hash + Objects.hashCode(this.orderLine);
        hash = 17 * hash + Objects.hashCode(this.typeProduct);
        hash = 17 * hash + Objects.hashCode(this.box);
        hash = 17 * hash + Objects.hashCode(this.productionLine);
        hash = 17 * hash + Objects.hashCode(this.product);
        hash = 17 * hash + this.DateStart;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Product other = (Product) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.DateStart != other.DateStart) {
            return false;
        }
        if (!Objects.equals(this.orderLine, other.orderLine)) {
            return false;
        }
        if (!Objects.equals(this.typeProduct, other.typeProduct)) {
            return false;
        }
        if (!Objects.equals(this.box, other.box)) {
            return false;
        }
        if (!Objects.equals(this.productionLine, other.productionLine)) {
            return false;
        }
        if (!Objects.equals(this.product, other.product)) {
            return false;
        }
        return true;
    }

    

    @Override
    public String toString() {
        return "Product{" + "id=" + id + ", orderLine=" + orderLine + ", typeProduct=" + typeProduct + ", box=" + box + ", productionLine=" + productionLine + ", product=" + product + ", DateStart=" + DateStart + '}';
    }
    
    
}
