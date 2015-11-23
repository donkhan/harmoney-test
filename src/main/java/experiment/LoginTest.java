package experiment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.json.JSONObject;

public class LoginTest extends BaseTest{

	
	private String userName;
	private String passWord;
	
	public LoginTest(String userName,String passWord){
		this.userName = userName;
		this.passWord = passWord;
	}
	
	

	@Override
	public JSONObject getPayLoad() {
		JSONObject user = new JSONObject();
		user.accumulate("id",userName);
		user.accumulate("password",passWord);
		return user;
	}

	
	public String login() throws ClientProtocolException, IOException{
		HttpResponse response = execute();
		System.out.println("Response Code : " 
	                + response.getStatusLine().getStatusCode());

		BufferedReader rd = new BufferedReader(
			new InputStreamReader(response.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		Header[] headers = response.getAllHeaders();
		for(Header header : headers){
			System.out.println(header.getName() + "   " + header.getValue());
			if(header.getName().equals("Set-Cookie")){
				String tokenValue = header.getValue();
				String x = tokenValue.substring(tokenValue.indexOf("=")+1,tokenValue.indexOf(";"));
				System.out.println("TV " + tokenValue);
				System.out.println(" X " +x);
				return x;
			}
		}
		return "";
	}

	@Override
	public String getURI() {
		return "/harmoney2/sessionService/authenticate";
	}



	@Override
	public void addExtraHeaders(HttpPost request) {
		// TODO Auto-generated method stub
		
	}
}
