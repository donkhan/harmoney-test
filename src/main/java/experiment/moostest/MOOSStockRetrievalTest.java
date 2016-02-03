package experiment;

import java.io.IOException;



public class MOOSStockRetrievalTest {
	
	public static void main(String args[]){
		String userName = "vteial";
		String passWord = "1";
		
		LoginTest test = new LoginTest(userName,passWord);
		try {
			String sessionId = test.login();
			MOOSGETTest stest = new MOOSGETTest(userName,sessionId);
			stest.setURI("/harmoney2/moos/get-stocks/Trichy/USD");
			stest.execute();
			System.out.println("");
			stest.setURI("/harmoney2/moos/get-stocks/Trichy/USE");
			stest.execute();
			System.out.println("");
			stest.setURI("/harmoney2/moos/get-stocks/Trichi/USD");
			stest.execute();
			System.out.println("");
			LogoutTest logoutTest = new LogoutTest(userName,sessionId);
			logoutTest.execute();
			
		} catch (IOException e) {
			
		}
	
	}

}
