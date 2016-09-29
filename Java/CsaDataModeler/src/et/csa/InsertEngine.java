
package et.csa;

import et.csa.bean.CsvRow;
import et.csa.bean.Record;
import et.csa.reader.CsvReader;
import et.csa.reader.DictionaryReader;
import et.csa.writer.InsertWriter;
import java.util.List;

/**
 * Use this class to create the inserts script
 * 
 * @author Istat Cooperation Unit
 */
public class InsertEngine {

    public static void main(String[] args) throws Exception {
        String schema = "ethiopian_census";
        List<Record> records = DictionaryReader.read(schema,"LesothoCensus2016.dcf");
        for (Record record : records) {
            // TODO
            List<CsvRow> rows = CsvReader.read(record);
            InsertWriter.execute(schema, record, rows, System.out);
        }
    }
        
}
