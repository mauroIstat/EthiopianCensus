
package et.csa.bean;

import java.util.LinkedList;
import java.util.List;

/**
 * This class represents a Record defined by the tag [Record] in the CSPro Dictionary
 * 
 * @author Istat Cooperation Unit
 */
public class Record {
    
    public String name;
    public Record mainRecord;
    public boolean isMainRecord = false;
    public List<Item> items = new LinkedList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public void addItem(Item it) {
        items.add(it);
    }
    
    public List<Item> getItems() {
        return items;
    }

    public Record getMainRecord() {
        return mainRecord;
    }

    public void setMainRecord(Record mainRecord) {
        this.mainRecord = mainRecord;
    }

    public boolean isMainRecord() {
        return isMainRecord;
    }

    public void setIsMainRecord(boolean isMainRecord) {
        this.isMainRecord = isMainRecord;
    }

}
