
package et.csa.bean;

/**
 * This class represents an Item defined by the tag [Item] in the CSPro Dictionary
 * 
 * @author Istat Cooperation Unit
 */
public class Item {
    
    public String name;
    public String type = "Number";
    public int length;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
    
}
