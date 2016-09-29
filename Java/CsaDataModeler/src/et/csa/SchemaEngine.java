
package et.csa;

import et.csa.bean.Record;
import et.csa.reader.DictionaryReader;
import et.csa.writer.SchemaWriter;
import java.util.List;

/**
 * Use this class to create the schema script
 * 
 * @author Istat Cooperation Unit
 */
public class SchemaEngine {

    public static void main(String[] args) throws Exception {
        String schema = "ethiopian_census";
        List<Record> records = DictionaryReader.read(schema,"LesothoCensus2016.dcf");
        SchemaWriter.execute(schema, records, System.out);
    }
        
}
