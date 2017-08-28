package greedy;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;

public class BFSAlgorithm {
	private ArrayList<ID> cidades;
	private LinkedList<ID> sucessores;
	private boolean visited[];
	
	public BFSAlgorithm(ArrayList<ID> lista){
		this.cidades = lista;
		this.sucessores = new LinkedList<ID>();
		visited = new boolean[lista.size()];
		for(int i = 0; i < visited.length; i++)
			visited[i] = false;
	}

	public double run(int start, int dest){
		ID actualCity;
		double cost = 0;
		ArrayList<Integer> vizIDs;
		ArrayList<ID> viz = new ArrayList<ID>();
		
		sucessores.add(cidades.get(start));
		while(!sucessores.isEmpty()){ 
			actualCity = sucessores.removeFirst();
			if(actualCity.id == dest){
				System.out.println("Final = "+actualCity.id);
				
				//Calculando custo e caminho
				int index = dest;
				int counter = 0;
				while(index != start){
					counter++;
					cost = cost+actualCity.costTo;
					index = actualCity.fromID;
					actualCity = cidades.get(index);
					//System.out.println("ID = "+index);
				}
				System.out.println("Custo = "+cost);
				System.out.println("Contador = "+counter);
				return cost;
			}
			else{
				//Ordenando a prioridade por custo dos vizinhos
				vizIDs = actualCity.adjList;
				viz.clear();
				for(int i = 0; i < actualCity.adjList.size(); i++){
					ID actViz = cidades.get(vizIDs.get(i));
					viz.add(actViz);
				}
				
				for(int i = 0; i < actualCity.adjList.size(); i++){
			
					int auxID = viz.get(i).id;
					if(!visited[auxID]){
						ID actViz = viz.get(i);
						actViz.costTo = distBetween(actViz.id, actualCity.id);
						actViz.h = distBetween(actViz.id, dest);
						actViz.fromID = actualCity.id;
						visited[auxID] = true;
						sucessores.add(actViz);
					}
				}
				//Reordenar a fila
				Collections.sort(sucessores);
			}
		}
		return cost;
	}
	
	public double distBetween(int ID1, int ID2){
		ID c1 = cidades.get(ID1);
		ID c2 = cidades.get(ID2);
		double x1, x2, y1, y2;
		x1 = c1.x;
		x2 = c2.x;
		y1 = c1.y;
		y2 = c2.y;
		return Math.sqrt(Math.pow(x1-x2,2)+Math.pow(y1-y2,2));
		
	}
	public static void printData(CSVReader c){
		for(int i = 0; i < c.lista.size(); i++){
			ID aux = c.lista.get(i);
			System.out.println("ID = "+String.valueOf(aux.id)+";  X = "+String.valueOf(aux.x)+";  Num viz = "+String.valueOf(aux.adjList.size()));
		}
		
	}
	public static void main(String[] args) throws ParseException{
		CSVReader c = new CSVReader();
		//printData(c);
		BFSAlgorithm BFS = new BFSAlgorithm(c.lista);
		BFS.run(203, 600);

	}
}