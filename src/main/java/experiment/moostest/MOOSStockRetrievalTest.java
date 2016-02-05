package experiment.moostest;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpResponse;

import experiment.authentication.LoginTest;
import experiment.authentication.LogoutTest;
import experiment.test.BaseGETTest;



public class MOOSStockRetrievalTest extends BaseGETTest{
	
	private String uri;
	
	public MOOSStockRetrievalTest(String userName,String sessionId){
		super(userName,sessionId);
	}
	
	@Override
	public String getURI() {
		return uri;
	}
	
	public void setURI(String s){
		this.uri = s;
	}
	
	public void print(HttpResponse response) throws IOException{
		InputStream is = response.getEntity().getContent();
		int j;
		while(( j = is.read()) != -1){
			System.out.print((char)j);
		}
	}
	
	public static void main(String args[]){
		String userName = "vteial";
		String passWord = "1";
		
		LoginTest test = new LoginTest(userName,passWord);
		try {
			String sessionId = test.login();
			MOOSStockRetrievalTest stest = new MOOSStockRetrievalTest(userName,sessionId);
			stest.setURI("/harmoney2/moos/get-stocks/Trichy/USD");
			stest.execute();
			System.out.println("");
			stest.setURI("/harmoney2/moos/get-stocks/Trichy/ALL");
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
