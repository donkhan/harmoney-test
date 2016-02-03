package experiment.authentication;

import java.io.IOException;

import org.apache.http.HttpResponse;

import experiment.test.BaseGETTest;



public class LogoutTest extends BaseGETTest{

	@Override
	public String getURI() {
		return "/harmoney2/sessionService/logout";
	}
	
	public LogoutTest(String userName,String sessionId){
		super(userName,sessionId);
	}

	public void print(HttpResponse response) throws IOException{
		System.out.println("\nLogged Out");
	}

	
}
