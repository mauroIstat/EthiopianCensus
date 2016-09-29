
package et.csa.writer;

import et.csa.bean.Item;
import et.csa.bean.Record;
import java.io.PrintStream;
import java.util.List;

/**
 * This class writes the SQL scripts to create a MySQL DB schema parsing the
 * data structure representing the CSPro Dictionary
 * 
 * @author Istat Cooperation Unit
 */
public class SchemaWriter {
    
    public static void execute(String schema, List<Record> records, PrintStream ps) {
        ps.println("CREATE SCHEMA "+schema+";");
        ps.println();
        
        for (Record record : records) {
            ps.println("CREATE TABLE "+schema+"."+record.getName()+" (");
            ps.println("    ID INT(9) UNSIGNED AUTO_INCREMENT,");
            if (!record.isMainRecord()) {
                ps.println("    "+record.getMainRecord().getName()+" INT(9) UNSIGNED NOT NULL,");
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
            if (!record.isMainRecord()) {
                ps.println("    INDEX ("+record.getMainRecord().getName()+"),");
                ps.println("    FOREIGN KEY ("+record.getMainRecord().getName()+") REFERENCES "+schema+"."+record.getMainRecord().getName()+"(id),");
            }
            ps.println("    PRIMARY KEY (ID)");
            ps.println(") ENGINE=INNODB;");
            ps.println();
        }
    }
    
}
