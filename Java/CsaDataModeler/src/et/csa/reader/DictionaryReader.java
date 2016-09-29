
package et.csa.reader;

import et.csa.SchemaEngine;
import et.csa.bean.Item;
import et.csa.bean.Record;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;

/**
 * This class reads the CSPro Dictionary
 * creating a data structure that represents the dictionary.
 * 
 * @author Istat Cooperation Unit
 */
public class DictionaryReader {
    
    public static List<Record> read(String schema, String fileName) throws Exception {
        Record mainRecord = null;
        List<Record> records = new LinkedList<>();
        try (InputStream in = SchemaEngine.class.getResourceAsStream(fileName)) {
            try (InputStreamReader fr = new InputStreamReader(in)) {
                try (BufferedReader br = new BufferedReader(fr)) {
                    String line;
                    Record record = null;
                    while ( (line=br.readLine())!=null ) {
                        switch (line) {
                            case "[Level]":
                                while ( (line=br.readLine())!=null ) {
                                    if (line.startsWith("Name")) {
                                        record = new Record();
                                        record.setName(getValue(line));
                                        record.setIsMainRecord(true);
                                        records.add(record);
                                        mainRecord = record;
                                    } else if (line.isEmpty()) {
                                        break;
                                    }
                                }   break;
                            case "[Item]":
                                Item item = new Item();
                                record.addItem(item);
                                while ( (line=br.readLine())!=null ) {
                                    if (line.startsWith("Name")) {
                                        item.setName(getValue(line));
                                    } else if (line.startsWith("DataType")) {
                                        item.setType(getValue(line));
                                    } else if (line.startsWith("Len")) {
                                        item.setLength(Integer.parseInt(getValue(line)));
                                    } else if (line.isEmpty()) {
                                        break;
                                    }
                                }   break;
                            case "[Record]":
                                record = new Record();
                                record.setMainRecord(mainRecord);
                                records.add(record);
                                while ( (line=br.readLine())!=null ) {
                                    if (line.startsWith("Name")) {
                                        record.setName(getValue(line));
                                    } else if (line.isEmpty()) {
                                        break;
                                    }
                                }   break;
                            default:
                                break;
                        }
                    }
                }
            }
        }
        return records;
    }
    
    private static String getValue(String s) {
        return s.split("=")[1];
    }

}
