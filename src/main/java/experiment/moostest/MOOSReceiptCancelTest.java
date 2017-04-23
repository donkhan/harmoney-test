package experiment.moostest;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONObject;

import experiment.test.BasePostTest;

public class MOOSReceiptCancelTest extends BasePostTest{
	public static void main(String args[]) throws ClientProtocolException, IOException{
		MOOSReceiptCancelTest ct = new MOOSReceiptCancelTest();
		ct.oneCycle();
	}

	@Override
	public String getPayLoad() {
		JSONObject obj =  new JSONObject();
		obj.put("reason", "User cancelled Visit");
		obj.put("branch-name","Trichy");
		obj.put("receipt-id", 87);
		System.out.println(obj.toString());
		return obj.toString();
	}

	@Override
	public String getURI() {
		return "/harmoney2/moos/cancel-transaction-by-receipt"; 
	}

}
