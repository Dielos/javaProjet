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
public class Box {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    // from file
    
    // links
    @ManyToOne(cascade={CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name="NBOXTYPE")
    private BoxType boxType;
    
    @ManyToOne(cascade={CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name="NASSOCIATEDORDER")
    private Order order; //could be replaced by a boolean. Represent the order that is using the box (only used during scheduling, should be `null` after scheduling process)
    
    @OneToMany(cascade={CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name="NBOX")
    private List<Product> products;
    
    // other
    private int num;

    // methods
    
    private void init() {
        boxType = null;
        order = null;
        products = new ArrayList<Product>();
    }
    
    public Box(int num) {
        init();
        
        this.num = num;
    }

    public Box() {
        init();
    }

    public boolean removeProduct(Product obj) {
        return products.remove(obj);
    }
    
    public boolean addProduct(Product obj) {
        if (obj.getBox() != null)
            return false;
        
        products.add(obj);
        obj.setBox(this);
        this.order = obj.getOrderLine().getOrder();
        return true;
    }
    
    public int getId() {
        return id;
    }

    public BoxType getBoxType() {
        return boxType;
    }

    public Order getOrder() {
        return order;
    }

    public List<Product> getProducts() {
        return products;
    }

    public int getNum() {
        return num;
    }

    public void setBoxType(BoxType boxType) {
        this.boxType = boxType;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.id;
        hash = 67 * hash + Objects.hashCode(this.boxType);
        hash = 67 * hash + Objects.hashCode(this.order);
        hash = 67 * hash + this.num;
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
        final Box other = (Box) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.num != other.num) {
            return false;
        }
        if (!Objects.equals(this.boxType, other.boxType)) {
            return false;
        }
        if (!Objects.equals(this.order, other.order)) {
            return false;
        }
        return true;
    }

    

    @Override
    public String toString() {
        return "Box{" + "id=" + id + ", boxType=" + boxType + ", order=" + order + ", num=" + num + '}';
    }
    
    
}
