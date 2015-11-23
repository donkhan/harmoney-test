package experiment;

import java.io.IOException;
import java.util.List;

public class MainThread extends Thread implements Runnable{
	
	private List<Transaction> transactions;
	private String userName;
	private String passWord;
	
	public MainThread(String userName,String passWord, List<Transaction> transactions){
		this.transactions = transactions;
		this.userName = userName;
		this.passWord = passWord;
	}
	
	@Override
	public void run() {
		LoginTest test = new LoginTest(userName,passWord);
		try {
			String sessionId = test.login();
			TransactionTest stest = new TransactionTest(transactions,userName);
			stest.execute(sessionId);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

	
}
