package experiment.moostest;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONObject;

import experiment.test.BasePostTest;

public class MOOSOrderCancelTest extends BasePostTest{
	public static void main(String args[]) throws ClientProtocolException, IOException{
		MOOSOrderCancelTest ct = new MOOSOrderCancelTest();
		ct.oneCycle();
	}
	
	@Override
	public String getPayLoad() {
		JSONObject request = new JSONObject();
		JSONArray payLoad = new JSONArray();
		payLoad.put(304);
		payLoad.put(303);
		request.put("branch-name", "Trichy");
		request.put("order-ids", payLoad);
		System.out.println("PayLoad " + request.toString());
		return request.toString();
	}

	@Override
	public String getURI() {
		return "/harmoney2/moos/cancel-orders"; 
	}
}
