package experiment;

import java.util.ArrayList;
import java.util.List;

public class TestProgram {

	
	public static void main(String args[]){
		String[] userNames = new String[]{"shaik"};
		String[] passWords = new String[]{"123"};
		
		Transaction[] transactions = new Transaction[]{
			new Transaction("B",9118,"USD",1,3.82,1),

		};
		
		for(int i = 0;i<userNames.length;i++){
			List<Transaction> l = new ArrayList<Transaction>();
			l.add(transactions[i]);
			MainThread mt = new MainThread(userNames[i],passWords[i],l);
			mt.start();
		}
	}
}
