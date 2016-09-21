
package cspro.bean;

import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author drovandi
 */
public class Record {
    
    public String name;
    public List<Item> items = new LinkedList<>();
    
    public Record(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    
    public void addItem(Item it) {
        items.add(it);
    }
    
    public List<Item> getItems() {
        return items;
    }
    
}
