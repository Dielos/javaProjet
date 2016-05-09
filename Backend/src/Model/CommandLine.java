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
public class CommandLine {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    // from file
    private int quantity;
    
    // links
    @ManyToOne(cascade={CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name="NCOMMAND")
    private Command command;
    
    @OneToMany(cascade={CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name="NCOMMANDLINE")
    private List<Item> items;

    // other
    
    // method
    
    private void init() {
        command = null;
        items = new ArrayList<Item>();
    }

    public CommandLine(int quantity) {
        init();
        this.quantity = quantity;
    }

    public CommandLine() {
        init();
    }

    public boolean removeItem(Item obj) {
        return items.remove(obj);
    }

    public boolean addItem(Item obj) {
        if (obj.getCommandLine() != null)
            return false;
        
        items.add(obj);
        obj.setCommandLine(this);
        return true;
    }

    public int getId() {
        return id;
    }

    public int getQuantity() {
        return quantity;
    }

    public Command getCommand() {
        return command;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setCommand(Command command) {
        this.command = command;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.id;
        hash = 89 * hash + this.quantity;
        hash = 89 * hash + Objects.hashCode(this.command);
        hash = 89 * hash + Objects.hashCode(this.items);
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
        final CommandLine other = (CommandLine) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.quantity != other.quantity) {
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
        return "CommandLine{" + "id=" + id + ", quantity=" + quantity + ", command=" + command + '}';
    }
    
    
}
