package experiment;

import java.io.IOException;



public class MooseTestProgram {
	
	public static void main(String args[]){
		String userName = "vteial";
		String passWord = "1";
		
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
