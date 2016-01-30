package experiment;

import java.io.IOException;



public class MOOSTestProgram {
	
	public static void main(String args[]){
		String userName = "vteial";
		String passWord = "1";
		
		LoginTest test = new LoginTest(userName,passWord);
		try {
			String sessionId = test.login();
			MOOSGETTest stest = new MOOSGETTest(userName,sessionId);
			stest.setURI("/harmoney2/moose/get-stocks/Trichy/USD");
			stest.execute();
			
			stest.setURI("/harmoney2/moose/get-stocks/Trichy/USE");
			stest.execute();
			
			stest.setURI("/harmoney2/moose/get-stocks/Trichi/USD");
			stest.execute();
			
			LogoutTest logoutTest = new LogoutTest(userName,sessionId);
			logoutTest.execute();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}

}
