package experiment.moneyboxtest;

import java.io.IOException;
import java.util.List;

import experiment.authentication.LoginTest;
import experiment.moneyboxtest.model.Transaction;

public class MainThread extends Thread implements Runnable{
	
	private List<Transaction> transactions;

	public MainThread(String userName,String passWord, List<Transaction> transactions){
		this.transactions = transactions;
	}
	
	@Override
	public void run() {
		LoginTest test = new LoginTest();
		try {
			String sessionId = test.login();
			for(int i = 0;i<2000;i++){
				TransactionTest stest = new TransactionTest(transactions,sessionId);
				stest.executeTransactions();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

	
}
