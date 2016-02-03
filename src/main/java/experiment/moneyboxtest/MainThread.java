package experiment.moneyboxtest;

import java.io.IOException;
import java.util.List;

import experiment.moneyboxtest.model.Transaction;
import experiment.test.LoginTest;

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
			for(int i = 0;i<2000;i++){
				TransactionTest stest = new TransactionTest(transactions,userName,sessionId);
				stest.executeTransactions();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

	
}
