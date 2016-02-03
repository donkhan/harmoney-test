package experiment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.json.JSONObject;

public class MOOSTransactionCancelTest extends BaseTest{
	private String userName;
	private String sessionId;
	
	public static void main(String args[]){
		String userName = "feroz";
		String passWord = "1";
		
		LoginTest loginTest = new LoginTest(userName,passWord);
		String sessionId = "";
		try {
			sessionId = loginTest.login();
			MOOSTransactionCancelTest ct = new MOOSTransactionCancelTest(userName,sessionId);
			ct.executeTransactions();
			
		} catch (Throwable e) {
			e.printStackTrace();
		} finally{
			LogoutTest logout = new LogoutTest(userName,sessionId);
			try {
				logout.execute();
			} catch (ClientProtocolException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public MOOSTransactionCancelTest(String un,String sessionId){
		this.userName = un;
		this.sessionId = sessionId;
	}
	
	@Override
	public String getPayLoad() {
		JSONObject obj =  new JSONObject();
		obj.put("reason", "User cancelled Visit");
		System.out.println(obj);
		return obj.toString();
	}

	@Override
	public String getURI() {
		return "/harmoney2/moos/cancel-transaction/Trichy/44/12"; 
	}

	@Override
	public void addExtraHeaders(HttpPost request) {
		request.addHeader("X-userId",userName);
		request.addHeader("Cookie","JSESSIONID="+sessionId);
	}
	
	public void executeTransactions() throws ClientProtocolException, IOException{
		HttpResponse response = super.execute();
		System.out.println("Response Code : " 
	                + response.getStatusLine().getStatusCode());

		BufferedReader rd = new BufferedReader(
			new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		System.out.println(result);

	}


}
