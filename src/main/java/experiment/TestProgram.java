package experiment;

import java.util.ArrayList;
import java.util.List;

public class TestProgram {

	
	public static void main(String args[]){
		String[] userNames = new String[]{"hnawas","hnawas","hnawas","hnawas"};
		String[] passWords = new String[]{"123","123","123","123"};
		
		Transaction[] transactions = new Transaction[]{
			new Transaction("S",7753,"USD",200,3.85,1),
			new Transaction("S",7753,"USD",100,3.85,1),
			new Transaction("B",7753,"USD",100,3.82,1),
			new Transaction("S",7753,"USD",50,3.85,1)
		};
		
		for(int i = 0;i<userNames.length;i++){
			List<Transaction> l = new ArrayList<Transaction>();
			l.add(transactions[i]);
			MainThread mt = new MainThread(userNames[i],passWords[i],l);
			mt.start();
		}
	}
}
