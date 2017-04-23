package experiment.authentication;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONObject;

import experiment.test.BasePostTest;


public class LoginTest extends BasePostTest{


	@Override
	public String getPayLoad() {
		JSONObject user = new JSONObject();
		user.accumulate("id",getUserName());
		user.accumulate("password",getPassWord());
		return user.toString();
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
		System.out.println(result);
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

	public static void main(String args[]) throws ClientProtocolException, IOException{
		LoginTest lt = new LoginTest();
		System.out.println(lt.login());
		lt.logout();
	}
}
