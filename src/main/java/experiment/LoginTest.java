package experiment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

public class LoginTest {

	public static void main(String args[]) throws ClientProtocolException, IOException{
		LoginTest lt = new LoginTest("vteial","");
		lt.login();
	}
	
	private String userName;
	private String passWord;
	
	public LoginTest(String userName,String passWord){
		this.userName = userName;
		this.passWord = passWord;
	}
	
	public String login() throws ClientProtocolException, IOException{
		String url = "http://localhost:8181/harmoney2/sessionService/authenticate";

		HttpClient client = new DefaultHttpClient();
		HttpPost request = new HttpPost(url);

		JSONObject user = new JSONObject();
		user.accumulate("id",userName);
		user.accumulate("password",passWord);
		
		request.addHeader("Content-Type","application/json;charset=UTF-8");
		request.addHeader("User-Agent", "STANDALONE_CODE");
		request.addHeader("Accept","application/json");

		String loginContent = user.toString();
		
		request.setEntity(new StringEntity(loginContent));
		HttpResponse response = client.execute(request);

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
}
