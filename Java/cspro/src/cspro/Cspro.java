
package cspro;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.LinkedList;
import java.util.List;

/**
 *
 * @author drovandi
 */
public class Cspro {

    public static void main(String[] args) throws Exception {
        String mainClass = "LESOTHO";
        List<Record> records = new LinkedList<>();
        try (InputStream in = Cspro.class.getResourceAsStream("LesothoCensus2016.dcf")) {
            try (InputStreamReader fr = new InputStreamReader(in)) {
                try (BufferedReader br = new BufferedReader(fr)) {
                    String s;
                    Record record = new Record(mainClass);
                    records.add(record);
                    while ( (s=br.readLine())!=null ) {
                        if ("[Item]".equals(s)) {
                            Item item = new Item();
                            record.addItem(item);
                            while ( (s=br.readLine())!=null ) {
                                if (s.startsWith("Name")) {
                                    item.setName(s.split("=")[1]);
                                } else if (s.startsWith("DataType")) {
                                    item.setType(s.split("=")[1]);
                                } else if (s.startsWith("Len")) {
                                    item.setLength(Integer.parseInt(s.split("=")[1]));
                                } else if (s.isEmpty()) {
                                    break;
                                }
                            }
                        } else if ("[Record]".equals(s)) {
                            while ( (s=br.readLine())!=null ) {
                                if (s.startsWith("Name")) {
                                    record = new Record(s.split("=")[1]);
                                    records.add(record);
                                } else if (s.isEmpty()) {
                                    break;
                                }
                            }
                        }
                    }
                }
            }
        }
        
        PrintStream ps = System.out;
        
        for (Record record : records) {
            ps.println("CREATE TABLE "+record.getName()+" (");
            ps.println("    ID INT(9) UNSIGNED AUTO_INCREMENT,");
            if (!mainClass.equals(record.getName())) {
                ps.println("    "+mainClass+" INT(9) UNSIGNED,");
                ps.println("    COUNTER INT(9) UNSIGNED,");
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
            ps.println("    PRIMARY KEY (ID)");
            ps.println(")");
            ps.println();
        }
    }
    
}
