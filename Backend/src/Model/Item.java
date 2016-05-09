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
public class Item {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    // from file
    
    // links
    @ManyToOne(cascade={CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name="NCOMMANDLINE")
    private CommandLine commandLine;
    
    @ManyToOne(cascade={CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name="NTYPEITEM")
    private TypeItem typeItem;
    
    @ManyToOne(cascade={CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name="NBOX")
    private Box box;
    
    @ManyToOne(cascade={CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name="NCOMMAND")
    private ProductionLine productionLine;
    
    @OneToOne(cascade={CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name="NPRODUCTIONLINE")
    private Item item;
    
    // other
    private int DateStart;
    
    // method
    
    private void init() {
        commandLine = null;
        typeItem = null;
        box = null;
        productionLine = null;
        item = null;
    }

    public Item(int DateStart) {
        init();
        
        this.DateStart = DateStart;
    }

    public Item() {
        init();
    }

    public int getId() {
        return id;
    }

    public CommandLine getCommandLine() {
        return commandLine;
    }

    public TypeItem getTypeItem() {
        return typeItem;
    }

    public Box getBox() {
        return box;
    }

    public ProductionLine getProductionLine() {
        return productionLine;
    }

    public Item getItem() {
        return item;
    }

    public int getDateStart() {
        return DateStart;
    }

    public void setCommandLine(CommandLine commandLine) {
        this.commandLine = commandLine;
    }

    public void setTypeItem(TypeItem typeItem) {
        this.typeItem = typeItem;
    }

    public void setBox(Box box) {
        this.box = box;
    }

    public void setProductionLine(ProductionLine productionLine) {
        this.productionLine = productionLine;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public void setDateStart(int DateStart) {
        this.DateStart = DateStart;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + this.id;
        hash = 61 * hash + Objects.hashCode(this.commandLine);
        hash = 61 * hash + Objects.hashCode(this.typeItem);
        hash = 61 * hash + Objects.hashCode(this.box);
        hash = 61 * hash + Objects.hashCode(this.productionLine);
        hash = 61 * hash + Objects.hashCode(this.item);
        hash = 61 * hash + this.DateStart;
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
        final Item other = (Item) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.DateStart != other.DateStart) {
            return false;
        }
        if (!Objects.equals(this.commandLine, other.commandLine)) {
            return false;
        }
        if (!Objects.equals(this.typeItem, other.typeItem)) {
            return false;
        }
        if (!Objects.equals(this.box, other.box)) {
            return false;
        }
        if (!Objects.equals(this.productionLine, other.productionLine)) {
            return false;
        }
        if (!Objects.equals(this.item, other.item)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Item{" + "id=" + id + ", commandLine=" + commandLine + ", typeItem=" + typeItem + ", box=" + box + ", productionLine=" + productionLine + ", item=" + item + ", DateStart=" + DateStart + '}';
    }
    
    
}
