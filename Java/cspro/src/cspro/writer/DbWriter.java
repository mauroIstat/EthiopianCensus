
package cspro.writer;

import cspro.bean.Item;
import cspro.bean.Record;
import java.io.PrintStream;
import java.util.List;

/**
 *
 * @author Guido
 */
public class DbWriter {
    
    public static void execute(String mainSchema, String mainClass, List<Record> records, PrintStream ps) {
        ps.println("CREATE SCHEMA "+mainSchema+";");
        ps.println();
        
        for (Record record : records) {
            ps.println("CREATE TABLE "+mainSchema+"."+record.getName()+" (");
            ps.println("    ID INT(9) UNSIGNED AUTO_INCREMENT,");
            if (!mainClass.equals(record.getName())) {
                ps.println("    "+mainClass+" INT(9) UNSIGNED NOT NULL,");
                ps.println("    COUNTER INT(9) UNSIGNED NOT NULL,");
            }
            for (Item item : record.getItems()) {
                String name = item.getName().toUpperCase();
                int length = item.getLength();
                switch (item.getType()) {
                    case "Alpha":
                        ps.println("    "+name+" CHAR("+length+"),");
                        break;
                    case "Number":
                        ps.println("    "+name+" INT("+length+"),");
                        break;
                    default:
                        ps.println(" data type unknown - "+item.getType());
                }
            }
            if (!mainClass.equals(record.getName())) {
                ps.println("    INDEX ("+mainClass+"),");
                ps.println("    FOREIGN KEY ("+mainClass+") REFERENCES "+mainSchema+"."+mainClass+"(id),");
            }
            ps.println("    PRIMARY KEY (ID)");
            ps.println(") ENGINE=INNODB;");
            ps.println();
        }
    }
    
}
