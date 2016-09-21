
package cspro;

import cspro.bean.Item;
import cspro.bean.Record;
import cspro.writer.DbWriter;
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
        String mainSchema = "ethiopian_census";
        String mainClass = null;
        List<Record> records = new LinkedList<>();
        try (InputStream in = Cspro.class.getResourceAsStream("LesothoCensus2016.dcf")) {
            try (InputStreamReader fr = new InputStreamReader(in)) {
                try (BufferedReader br = new BufferedReader(fr)) {
                    String s;
                    Record record = null;
                    while ( (s=br.readLine())!=null ) {
                        if ("[Level]".equals(s)) {
                            while ( (s=br.readLine())!=null ) {
                                if (s.startsWith("Name")) {
                                    mainClass = s.split("=")[1];
                                    record = new Record(mainClass);
                                    records.add(record);
                                } else if (s.isEmpty()) {
                                    break;
                                }
                            }
                        } else if ("[Item]".equals(s)) {
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
        
        DbWriter.execute(mainSchema, mainClass, records, System.out);
    }
    
}
