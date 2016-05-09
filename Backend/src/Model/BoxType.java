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
public class BoxType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    // from file
    private String boxName;
    private int height;
    private int width;
    private int cost;
    
    // links
    @OneToMany(cascade={CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name="NBOXTYPE")
    private List<Box> boxs;
    
    // other
    
    // method
    
    private void init() {
        boxs = new ArrayList<Box>();
    }
    
    public BoxType(String boxName, int height, int width, int cost) {
        this.boxName = boxName;
        this.height = height;
        this.width = width;
        this.cost = cost;
    }

    public BoxType() {
    }
    
    public boolean removeBox(Box obj) {
        return boxs.remove(obj);
    }

    public boolean addBox(Box obj) {
        if (obj.getBoxType() != null)
            return false;
        
        boxs.add(obj);
        obj.setBoxType(this);
        return true;
    }

    public int getId() {
        return id;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public int getCost() {
        return cost;
    }

    public List<Box> getBoxs() {
        return boxs;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 11 * hash + this.id;
        hash = 11 * hash + this.height;
        hash = 11 * hash + this.width;
        hash = 11 * hash + this.cost;
        hash = 11 * hash + Objects.hashCode(this.boxs);
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
        final BoxType other = (BoxType) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.height != other.height) {
            return false;
        }
        if (this.width != other.width) {
            return false;
        }
        if (this.cost != other.cost) {
            return false;
        }
        if (!Objects.equals(this.boxs, other.boxs)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BoxType{" + "id=" + id + ", height=" + height + ", width=" + width + ", cost=" + cost + '}';
    }
    
    
}
