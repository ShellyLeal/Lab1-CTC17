package greedy;

import java.util.ArrayList;

public class ID implements Comparable<ID> {
double x;
double y;
int id;
double h;
double costTo = 0;
int fromID = 0;
ArrayList<Integer> adjList;
ID(int idd, double a, double b){x=a;y=b;id = idd;
adjList = new ArrayList<Integer>();
}
@Override
public int compareTo(ID next) {
	if(this.h < next.h){
		return -1;
	}
	if(this.h > next.h){
		return 1;
	}
	return 0;
}
public void addCity(String cityID){
	adjList.add(new Integer(cityID));
}
}
