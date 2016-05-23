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
import javax.persistence.Table;

/**
 *
 * @author Robin
 */
@Entity()
@Table(name="ORDER_")  // persistence doesn't seem to escape the table name in its request, leading to error with reserved word "ORDER"
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    // from file
    private String orderName;
    private int stockMin;
    private int dateLimit;
    private float penality;
    
    // links
    @OneToMany(cascade={CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name="NORDER")
    private List<OrderLine> orderLines;

    // other
    private int startProductionDate;
    private int sendingDate;
    
    // method
    
    public int getTotalProductsSize() {
        int sum=0;
        for (OrderLine o : orderLines)
            sum += o.getTotalProductSize();
        
        return sum;
    }
    
    private void init() {
        orderLines = new ArrayList<OrderLine>();
    }

    public Order(String orderName, int stockMin, int dateLimit, float penality) {
        init();
        
        this.orderName = orderName;
        this.stockMin = stockMin;
        this.dateLimit = dateLimit;
        this.penality = penality;
    }

    public Order() {
        init();
    }

    public boolean removeOrderLine(OrderLine obj) {
        return orderLines.remove(obj);
    }

    public boolean addOrderLine(OrderLine obj) {
        if (obj.getOrder() != null)
            return false;
        
        orderLines.add(obj);
        obj.setOrder(this);
        return true;
    }

    public int getId() {
        return id;
    }

    public String getOrderName() {
        return orderName;
    }

    public int getSendingDate() {
        return sendingDate;
    }
    
    

    public int getStockMin() {
        return stockMin;
    }

    public int getDateLimit() {
        return dateLimit;
    }

    public float getPenality() {
        return penality;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    public int getStartProductionDate() {
        return startProductionDate;
    }

    public void setSendingDate(int sendingDate) {
        this.sendingDate = sendingDate;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public void setStockMin(int stockMin) {
        this.stockMin = stockMin;
    }

    public void setDateLimit(int dateLimit) {
        this.dateLimit = dateLimit;
    }

    public void setPenality(float penality) {
        this.penality = penality;
    }

    public void setStartProductionDate(int startProductionDate) {
        this.startProductionDate = startProductionDate;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + this.id;
        hash = 37 * hash + Objects.hashCode(this.orderName);
        hash = 37 * hash + this.stockMin;
        hash = 37 * hash + this.dateLimit;
        hash = 37 * hash + Float.floatToIntBits(this.penality);
        hash = 37 * hash + this.startProductionDate;
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
        final Order other = (Order) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.stockMin != other.stockMin) {
            return false;
        }
        if (this.dateLimit != other.dateLimit) {
            return false;
        }
        if (this.penality != other.penality) {
            return false;
        }
        if (this.startProductionDate != other.startProductionDate) {
            return false;
        }
        if (!Objects.equals(this.orderName, other.orderName)) {
            return false;
        }
        return true;
    }

    

    @Override
    public String toString() {
        return "Order{" + "id=" + id + ", stockMin=" + stockMin + ", dateLimit=" + dateLimit + ", penality=" + penality + ", startProductionDate=" + startProductionDate + '}';
    }
    
    
}
