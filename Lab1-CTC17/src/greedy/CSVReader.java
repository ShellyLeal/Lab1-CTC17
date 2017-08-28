package greedy;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Locale;
public class CSVReader {
	ArrayList<ID> lista = new ArrayList<ID>();

	
	//Greedy graph;
    public CSVReader() throws ParseException {
        String csvFile = "C:\\Users\\shell\\Desktop\\uruguay.csv";
        String line = "";
        String cvsSplitBy = ";";
        int v = 1;
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
        	lista.add(new ID(0,0,0));
            while ((line = br.readLine()) != null) {
            	
                String[] data = line.split(cvsSplitBy);
                double[] floatArray = new double[data.length];
            	for(int i=0;i<data.length-1;i++){
            	    NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
					Number listArray = format.parse(data[i].trim());
					floatArray[i] = listArray.doubleValue();
            	}
            	ID id = new ID(v,floatArray[1], floatArray[2]);
            	for(int i=3;i<data.length-1;i++){
            		
            			//double weight = Math.hypot(lista.get((int) (floatArray[0]-1)).x -lista.get((int) (floatArray[i]-1)).x , lista.get((int) (floatArray[0]-1)).y -lista.get((int) (floatArray[i]-1)).y );
            		
            			id.addCity(String.valueOf((int)floatArray[i]));
     
    					
            		
        		
            }
            	lista.add(id);
            	v++;
            	
            }
        }
         catch (IOException e) {
            e.printStackTrace();
        }
        
        
       


    }

}
