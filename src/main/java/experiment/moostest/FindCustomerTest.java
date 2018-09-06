package experiment.moostest;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpResponse;

import experiment.authentication.LoginTest;
import experiment.authentication.LogoutTest;
import experiment.test.BaseGETTest;

public class FindCustomerTest extends BaseGETTest{

	public FindCustomerTest(String userName, String sessionId) {
		super(userName, sessionId);
	}

	public static void main(String args[]){
		String userName = "sadmin";
		String passWord = "A123456*";
		
		LoginTest test = new LoginTest(userName,passWord);
		try {
			String sessionId = test.login();
			FindCustomerTest stest = new FindCustomerTest(userName,sessionId);
			stest.execute();
			System.out.println("");			
			LogoutTest logoutTest = new LogoutTest(userName,sessionId);
			logoutTest.execute();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}

	private String icNo = "920519016204";

	@Override
	public String getURI() {
		return "/harmoney2/customers/findByIdentificationNumber/" + icNo;
	}

	@Override
	public void print(HttpResponse response) throws IOException {
		InputStream is = response.getEntity().getContent();
		int i = -1;
		while(( i = is.read())!= -1){
			System.out.print((char)i);
		}
		System.out.println("");
	}

}
