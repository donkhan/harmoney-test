package experiment.moostest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.json.JSONObject;

import experiment.authentication.LoginTest;
import experiment.authentication.LogoutTest;
import experiment.test.BasePOSTTest;

public class CreateCustomerTest extends BasePOSTTest {

	String userName;
	String sessionId;
	
	public CreateCustomerTest(String userName, String sessionId) {
		this.userName = userName;
		this.sessionId = sessionId;
	}

	public static void main(String args[]){
		String userName = "sadmin";
		String passWord = "A123456*";
		
		LoginTest loginTest = new LoginTest(userName,passWord);
		String sessionId = "";
		try {
			sessionId = loginTest.login();
			CreateCustomerTest dt = new CreateCustomerTest(userName,sessionId);
			HttpResponse response = dt.execute();
			BufferedReader rd = new BufferedReader(
					new InputStreamReader(response.getEntity().getContent()));

				StringBuffer result = new StringBuffer();
				String line = "";
				while ((line = rd.readLine()) != null) {
					result.append(line);
				}
				System.out.println(result);
			
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
	
	@Override
	public String getPayLoad() {
		JSONObject payLoad = new JSONObject();
		payLoad.put("accountId", 0);
		payLoad.put("address", "E323 Wilson Street");
		payLoad.put("cityCode","C");
		payLoad.put("countryCode","Malaysia");
		payLoad.put("dob","2000-04-04T17:16:32.227Z");
		payLoad.put("emailId","a@a.com");
		payLoad.put("mobileNo","12323");
		payLoad.put("firstName","c1");
		payLoad.put("gender","M");
		payLoad.put("identityCardNumber","1232323");
		payLoad.put("id",0);
		payLoad.put("lastName","l1");
		payLoad.put("maritalStatus","M");
		payLoad.put("nationality", "Malaysia");
		payLoad.put("occupation", "Doctor");
		payLoad.put("ofac", false);
		payLoad.put("pbl", false);
		payLoad.put("pep", false);
		payLoad.put("postalCode", "1212");
		payLoad.put("stateCode", "D");
		payLoad.put("resident", "Y");
		System.out.println("PayLoad " + payLoad.toString());
		return payLoad.toString();
	}

	@Override
	public String getURI() {
		return "/harmoney2/customers/customer";
	}

	@Override
	public void addExtraHeaders(HttpPost request) {
		request.addHeader("X-userId",userName);
		request.addHeader("Cookie","JSESSIONID="+sessionId);
	}
}
