package experiment.moostest;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONObject;

import experiment.test.BasePostTest;

public class CreateCustomerTest extends BasePostTest{
	
	public static void main(String args[]) throws ClientProtocolException, IOException{
		new CreateCustomerTest().executeAndPrint();
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
}
