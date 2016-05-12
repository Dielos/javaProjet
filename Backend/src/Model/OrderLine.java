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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author Robin
 */
@Entity
public class OrderLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    // from file
    private int quantity;
    
    // links
    @ManyToOne(cascade={CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name="NORDER")
    private Order order;
    
    @ManyToOne(cascade={CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name="NPRODUCTTYPE")
    private ProductType typeProduct;
    
    @OneToMany(cascade={CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name="NORDERLINE")
    private List<Product> products;

    // other
    
    // method
    
    private void init() {
        order = null;
        products = new ArrayList<Product>();
    }

    public OrderLine(int quantity, ProductType typeProduct) {
        init();
        this.quantity = quantity;
        this.typeProduct = typeProduct;
    }

    public OrderLine() {
        init();
    }

    public boolean removeProduct(Product obj) {
        return products.remove(obj);
    }

    public boolean addProduct(Product obj) {
        if (obj.getOrderLine() != null)
            return false;
        
        products.add(obj);
        obj.setOrderLine(this);
        return true;
    }

    public int getId() {
        return id;
    }

    public ProductType getTypeProduct() {
        return typeProduct;
    }

    public int getQuantity() {
        return quantity;
    }

    public Order getOrder() {
        return order;
    }

    public List<Product> getProducts() {
        return products;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setTypeProduct(ProductType typeProduct) {
        this.typeProduct = typeProduct;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 13 * hash + this.id;
        hash = 13 * hash + this.quantity;
        hash = 13 * hash + Objects.hashCode(this.order);
        hash = 13 * hash + Objects.hashCode(this.typeProduct);
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
        final OrderLine other = (OrderLine) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.quantity != other.quantity) {
            return false;
        }
        if (!Objects.equals(this.order, other.order)) {
            return false;
        }
        if (!Objects.equals(this.typeProduct, other.typeProduct)) {
            return false;
        }
        return true;
    }

    

    @Override
    public String toString() {
        return "OrderLine{" + "id=" + id + ", quantity=" + quantity + ", order=" + order + '}';
    }
    
    
}
