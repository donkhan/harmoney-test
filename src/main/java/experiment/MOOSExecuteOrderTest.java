package experiment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.json.JSONArray;

public class MOOSExecuteOrderTest extends BaseTest {

	private String userName;
	private String sessionId;

	public MOOSExecuteOrderTest(String userName, String sessionId) {
		this.userName = userName;
		this.sessionId = sessionId;
	}

	public void executeTransactions() throws ClientProtocolException,
			IOException {
		HttpResponse response = super.execute();
		System.out.println("Response Code : "
				+ response.getStatusLine().getStatusCode());

		BufferedReader rd = new BufferedReader(new InputStreamReader(response
				.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		System.out.println(result);

	}

	@Override
	public String getPayLoad() {
		JSONArray payLoad = new JSONArray();
		payLoad.put(301);
		payLoad.put(302);
		System.out.println("PayLoad " + payLoad.toString());
		return payLoad.toString();
	}

	@Override
	public String getURI() {
		return "/harmoney2/moos/execute-orders/Trichy";
	}

	@Override
	public void addExtraHeaders(HttpPost request) {
		request.addHeader("X-userId", userName);
		request.addHeader("Cookie", "JSESSIONID=" + sessionId);
	}

	public static void main(String args[]) throws ClientProtocolException,
			IOException {
		{
			String userName = "feroz";
			String passWord = "1";

			LoginTest loginTest = new LoginTest(userName, passWord);
			String sessionId = "";
			try {
				sessionId = loginTest.login();
				MOOSExecuteOrderTest dt = new MOOSExecuteOrderTest(userName, sessionId);
				dt.executeTransactions();

			} catch (Throwable e) {
				e.printStackTrace();
			} finally {
				LogoutTest logout = new LogoutTest(userName, sessionId);
				try {
					logout.execute();
				} catch (ClientProtocolException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
}
