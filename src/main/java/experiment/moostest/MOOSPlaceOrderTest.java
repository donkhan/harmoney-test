package experiment.moostest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.json.JSONArray;
import org.json.JSONObject;

import experiment.authentication.LoginTest;
import experiment.authentication.LogoutTest;
import experiment.moostest.model.MOOSOrder;
import experiment.test.BaseTest;

public class MOOSPlaceOrderTest extends BaseTest{
	
	private List<MOOSOrder> transactions;
	private String userName;
	private String sessionId;
	private String branchName;
	
	public List<MOOSOrder> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<MOOSOrder> transactions) {
		this.transactions = transactions;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getSessionId() {
		return sessionId;
	}
	public void setSessionId(String sessionId) {
		this.sessionId = sessionId;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public static void main(String args[]){
		String userName = "feroz";
		String passWord = "1";
		
		LoginTest loginTest = new LoginTest(userName,passWord);
		String sessionId = "";
		try {
			sessionId = loginTest.login();
			List<MOOSOrder> list = new ArrayList<MOOSOrder>();
			list.add(new MOOSOrder("USD",10,1));
			list.add(new MOOSOrder("GBP",10,5));
			MOOSPlaceOrderTest dt = new MOOSPlaceOrderTest(list,userName,sessionId);
			dt.setBranchName("Trichy");
			dt.executeTransactions();
			
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
	public MOOSPlaceOrderTest(List<MOOSOrder> transactions,String userName,String sessionId){
		this.transactions = transactions;
		this.userName = userName;
		this.sessionId = sessionId;
	}
	
	private void setReceipt(JSONObject request){
		long pickUpTime = System.currentTimeMillis() + 3 * 24 * 60 * 60 * 1000;
		request.put("pick-up-time", pickUpTime);
		request.put("branch-name",getBranchName());
		setTransactions(request);
	}
	
	
	
	private double setTransactions(JSONObject request){
		double total = 0d;
		JSONArray atransactions = new JSONArray();
		for(MOOSOrder orig : transactions){
			JSONObject tran = new JSONObject();
			tran.put("currency-id",orig.getCurrencyId());
			tran.put("unit",orig.getExchangeUnit());
			tran.put("exchange-rate",orig.getExchangeRate());
			atransactions.put(tran);
		}
		request.put("orders",atransactions);
		return total;
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

	@Override
	public String getPayLoad() {
		JSONObject payLoad = new JSONObject();
		setReceipt(payLoad);
		System.out.println("PayLoad " + payLoad.toString());
		return payLoad.toString();
	}

	@Override
	public String getURI() {
		return "/harmoney2/moos/place-orders";
	}

	@Override
	public void addExtraHeaders(HttpPost request) {
		request.addHeader("X-userId",userName);
		request.addHeader("Cookie","JSESSIONID="+sessionId);
	}
	
	
	
}
