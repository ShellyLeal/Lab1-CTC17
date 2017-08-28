package astar;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
public class CSVReader {
	ArrayList<ID> lista = new ArrayList<ID>();
	Vertex[] vertices = new Vertex[735];
	Edge[] edges = new Edge[1500];
	
	//Greedy graph;
    public CSVReader() throws ParseException {
        String csvFile = "C:\\Users\\shell\\Desktop\\uruguay.csv";
        String line = "";
        String cvsSplitBy = ";";
        int v = 0;
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
            	
                String[] data = line.split(cvsSplitBy);
                double[] floatArray = new double[data.length];
            	for(int i=0;i<data.length-1;i++){
            	    NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
					Number listArray = format.parse(data[i].trim());
					floatArray[i] = listArray.doubleValue();
            	}
            	Vertex vnew = new Vertex(String.valueOf(floatArray[0]));
            	vnew.adjacencies = new Edge[100];
                vertices[v]= vnew;
        		lista.add(new ID(floatArray[1], floatArray[2]));
                v++;
            	
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
             v = 0;
             //for(int i=0;i<lista.size();i++)
         	//	System.out.println(lista.get(i).x);
            while ((line = br.readLine()) != null) {
            	int edg = 0;
                String[] data = line.split(cvsSplitBy);
                double[] floatArray = new double[data.length];
            	for(int i=0;i<data.length-1;i++){
            	    NumberFormat format = NumberFormat.getInstance(Locale.FRANCE);
					Number listArray = format.parse(data[i].trim());
					floatArray[i] = listArray.doubleValue();	
            	}
            	
            	vertices[v].toGoal = (double) Math.sqrt(Math.pow((lista.get(v).x -lista.get(602).x ),2)+
           				Math.pow((lista.get(v).y -lista.get(602).y),2));
            	for(int i=0;i<data.length-1;i++){
            		if(i>=3){
            			double weight = (double) Math.sqrt(Math.pow((lista.get((int) (floatArray[0]-1)).x -lista.get((int) (floatArray[i]-1)).x ),2)+
                				Math.pow((lista.get((int) (floatArray[0]-1)).y -lista.get((int) (floatArray[i]-1)).y ),2));
            			vertices[v].adjacencies[edg] = new Edge(vertices[(int)(floatArray[i]-1)],weight);
            			
    					edg++;
            		}
            	}
            	v++;
            }
            
          

        } catch (IOException e) {
            e.printStackTrace();
        }
}
       


    }

