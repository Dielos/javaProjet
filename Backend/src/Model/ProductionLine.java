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
import javax.persistence.OneToOne;

/**
 *
 * @author Robin
 */
@Entity
public class ProductionLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    // from file
    private int num; // could be replaced by the id
    
    // links
    @OneToOne(cascade={CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name="NPRODUCTIONLINE")
    private ProductType currentProduction;
    
    // other
    private int dateAvailable;
    
    // method
    
    private void init() {
        currentProduction = null;
    }

    public ProductionLine() {
        init();
    }

    public ProductionLine(int num, int dateAvailable) {
        init();
        
        this.num = num;
        this.dateAvailable = dateAvailable;
    }
    
    public int produce(ProductType pt) {
        if (currentProduction != pt) {
            currentProduction = pt;
            dateAvailable += pt.getSetupTime();
        }
        int tmp = dateAvailable;
        dateAvailable += pt.getProdTime();
        
        return tmp;
    }

    public int getId() {
        return id;
    }

    public int getNum() {
        return num;
    }

    public ProductType getProduct() {
        return currentProduction;
    }

    public int getDateAvailable() {
        return dateAvailable;
    }

    public void setNum(int num) {
        this.num = num;
    }

    public void setProduct(ProductType currentProduction) {
        this.currentProduction = currentProduction;
    }

    public void setDateAvailable(int dateAvailable) {
        this.dateAvailable = dateAvailable;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.id;
        hash = 97 * hash + this.num;
        hash = 97 * hash + Objects.hashCode(this.currentProduction);
        hash = 97 * hash + this.dateAvailable;
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
        final ProductionLine other = (ProductionLine) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.num != other.num) {
            return false;
        }
        if (this.dateAvailable != other.dateAvailable) {
            return false;
        }
        if (!Objects.equals(this.currentProduction, other.currentProduction)) {
            return false;
        }
        return true;
    }

    

    @Override
    public String toString() {
        return "ProductionLine{" + "id=" + id + ", num=" + num + ", product=" + currentProduction + ", dateAvailable=" + dateAvailable + '}';
    }
    
    
}
