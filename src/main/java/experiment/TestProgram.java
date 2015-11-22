package experiment;

import java.util.ArrayList;
import java.util.List;

public class TestProgram {

	
	public static void main(String args[]){
		String[] userNames = new String[]{"vteial","vteial"};
		String[] passWords = new String[]{"",""};
		Transaction[] transactions = new Transaction[]{
			new Transaction("S",52,"USD",10,3.5,1),
			new Transaction("S",52,"USD",5,3.45,1)
		};
		
		for(int i = 0;i<userNames.length;i++){
			List<Transaction> l = new ArrayList<Transaction>();
			l.add(transactions[i]);
			MainThread mt = new MainThread(userNames[i],passWords[i],l);
			mt.start();
		}
	}
}
