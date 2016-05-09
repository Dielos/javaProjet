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
    BoxType boxType;
    
    @ManyToOne(cascade={CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name="NCOMMAND")
    Command command; //could be replaced by a boolean. Represent the command that is using the box (only used during scheduling, should be `null` after scheduling process)
    
    @OneToMany(cascade={CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name="NBOX")
    List<Item> items;
    
    // other
    int num;

    // methods
    
    private void init() {
        boxType = null;
        command = null;
        items = new ArrayList<Item>();
    }
    
    public Box(BoxType boxType, Command command, List<Item> items, int num) {
        init();
        
        this.boxType = boxType;
        this.command = command;
        this.items = items;
        this.num = num;
    }

    public Box() {
        init();
    }

    public boolean removeItem(Item obj) {
        return items.remove(obj);
    }
    
    public boolean addItem(Item obj) {
        if (obj.box != null)
            return false;
        
        items.add(obj);
        obj.box = this;
        return true;
    }
    
    public int getId() {
        return id;
    }

    public BoxType getBoxType() {
        return boxType;
    }

    public Command getCommand() {
        return command;
    }

    public List<Item> getItems() {
        return items;
    }

    public int getNum() {
        return num;
    }

    public void setBoxType(BoxType boxType) {
        this.boxType = boxType;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + this.id;
        hash = 89 * hash + Objects.hashCode(this.boxType);
        hash = 89 * hash + Objects.hashCode(this.command);
        hash = 89 * hash + Objects.hashCode(this.items);
        hash = 89 * hash + this.num;
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
        if (!Objects.equals(this.command, other.command)) {
            return false;
        }
        if (!Objects.equals(this.items, other.items)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Box{" + "id=" + id + ", boxType=" + boxType + ", command=" + command + ", num=" + num + '}';
    }
    
    
}
