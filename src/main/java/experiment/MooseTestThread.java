package experiment;

import java.io.IOException;

public class MooseTestThread extends Thread implements Runnable{
	
	private String userName;
	private String passWord;
	
	public MooseTestThread(String userName,String passWord){
		this.userName = userName;
		this.passWord = passWord;
	}
	
	@Override
	public void run() {
		LoginTest test = new LoginTest(userName,passWord);
		try {
			String sessionId = test.login();
			MooseGETTest stest = new MooseGETTest(userName,sessionId);
			stest.setURI("/harmoney2/get-stocks/Trichy/USD");
			stest.execute();
			
			stest.setURI("/harmoney2/get-stocks/Trichy/USE");
			stest.execute();
			
			stest.setURI("/harmoney2/get-stocks/Trichi/USD");
			stest.execute();
			
			LogoutTest logoutTest = new LogoutTest(userName,sessionId);
			logoutTest.execute();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}

	
}
