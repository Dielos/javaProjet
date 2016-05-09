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

/**
 *
 * @author Robin
 */
@Entity
public class TypeItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    // from file
    private String itemName;
    private int setupTime;
    private int prodTime;
    private int height;
    private int width;
    private int empileMax;

    // links
    @OneToMany(cascade={CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name="NTYPEITEM")
    private List<Item> items;
    
    // other
    
    // method
    
    private void init() {
        items = new ArrayList<Item>();
    }

    public TypeItem() {
        init();
    }

    public TypeItem(String itemName, int setupTime, int prodTime, int height, int width, int empileMax) {
        init();
        
        this.itemName = itemName;
        this.setupTime = setupTime;
        this.prodTime = prodTime;
        this.height = height;
        this.width = width;
        this.empileMax = empileMax;
    }

    public boolean removeItem(Item obj) {
        return items.remove(obj);
    }

    public boolean addItem(Item obj) {
        if (obj.getTypeItem != null)
            return false;
        
        items.add(obj);
        obj.setTypeItem(this);
        return true;
    }

    public int getId() {
        return id;
    }

    public String getItemName() {
        return itemName;
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

    public List<Item> getItems() {
        return items;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
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
        int hash = 7;
        hash = 37 * hash + this.id;
        hash = 37 * hash + this.setupTime;
        hash = 37 * hash + this.prodTime;
        hash = 37 * hash + this.height;
        hash = 37 * hash + this.width;
        hash = 37 * hash + this.empileMax;
        hash = 37 * hash + Objects.hashCode(this.items);
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
        final TypeItem other = (TypeItem) obj;
        if (this.id != other.id) {
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
        if (!Objects.equals(this.items, other.items)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TypeItem{" + "id=" + id + ", setupTime=" + setupTime + ", prodTime=" + prodTime + ", height=" + height + ", width=" + width + ", empileMax=" + empileMax + '}';
    }
    
    
}
