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
public class Command {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    // from file
    int stockMin;
    int dateLimit;
    int penality;
    
    // links
    @OneToMany(cascade={CascadeType.PERSIST,CascadeType.REMOVE})
    @JoinColumn(name="NCOMMAND")
    List<CommandLine> commandLines;

    // other
    int startProductionDate;
    
    // method
    
    private void init() {
        commandLines = new ArrayList<CommandLine>();
    }

    public Command(int stockMin, int dateLimit, int penality, int startProductionDate) {
        init();
        
        this.stockMin = stockMin;
        this.dateLimit = dateLimit;
        this.penality = penality;
        this.startProductionDate = startProductionDate;
    }

    public Command() {
        init();
    }

    public boolean removeCommandLine(CommandLine obj) {
        return commandLines.remove(obj);
    }

    public boolean addCommandLine(CommandLine obj) {
        if (obj.command != null)
            return false;
        
        commandLines.add(obj);
        obj.command = this;
        return true;
    }

    public int getId() {
        return id;
    }

    public int getStockMin() {
        return stockMin;
    }

    public int getDateLimit() {
        return dateLimit;
    }

    public int getPenality() {
        return penality;
    }

    public List<CommandLine> getCommandLines() {
        return commandLines;
    }

    public int getStartProductionDate() {
        return startProductionDate;
    }

    public void setStockMin(int stockMin) {
        this.stockMin = stockMin;
    }

    public void setDateLimit(int dateLimit) {
        this.dateLimit = dateLimit;
    }

    public void setPenality(int penality) {
        this.penality = penality;
    }

    public void setStartProductionDate(int startProductionDate) {
        this.startProductionDate = startProductionDate;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + this.id;
        hash = 59 * hash + this.stockMin;
        hash = 59 * hash + this.dateLimit;
        hash = 59 * hash + this.penality;
        hash = 59 * hash + Objects.hashCode(this.commandLines);
        hash = 59 * hash + this.startProductionDate;
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
        final Command other = (Command) obj;
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
        if (!Objects.equals(this.commandLines, other.commandLines)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Command{" + "id=" + id + ", stockMin=" + stockMin + ", dateLimit=" + dateLimit + ", penality=" + penality + ", startProductionDate=" + startProductionDate + '}';
    }
    
    
}
