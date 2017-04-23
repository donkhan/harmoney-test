package experiment.moostest;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONObject;

import experiment.test.BasePostTest;

public class MOOSExecuteOrderTest extends BasePostTest {
	public MOOSExecuteOrderTest() {
	}

	@Override
	public String getPayLoad() {
		JSONObject request = new JSONObject();
		JSONArray payLoad = new JSONArray();
		payLoad.put(304);
		payLoad.put(303);
		request.put("branch-name", "MADURAI");
		request.put("order-ids", payLoad);
		System.out.println("PayLoad " + request.toString());
		return request.toString();
	}

	@Override
	public String getURI() {
		return "/harmoney2/moos/execute-orders";
	}


	public static void main(String args[]) throws ClientProtocolException,
			IOException {
		MOOSExecuteOrderTest dt = new MOOSExecuteOrderTest();
		dt.oneCycle();
	}
}
