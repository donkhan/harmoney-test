package experiment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.json.JSONArray;
import org.json.JSONObject;

public class DealerTransactionTest extends BaseTest{
	
	private List<DealerTransaction> transactions;
	private String userName;
	private String sessionId;
	
	public static void main(String args[]) throws ClientProtocolException, IOException{
		
	}
	
	public DealerTransactionTest(List<DealerTransaction> transactions,String userName,String sessionId){
		this.transactions = transactions;
		this.userName = userName;
		this.sessionId = sessionId;
	}
	
	private void setReceipt(JSONObject request){
		double t = setTransactions(request);
	}
	
	
	
	private double setTransactions(JSONObject request){
		JSONObject tran = new JSONObject();
		double total = 0d;
		JSONArray atransactions = new JSONArray();
		for(DealerTransaction orig : transactions){
			tran.put("currencyId",orig.getCurrencyId());
			tran.put("exchangeUnit",orig.getExchangeUnit());
			tran.put("exchangeRate",orig.getExchangeRate());
			atransactions.put(tran);
		}
		request.put("transactions",atransactions);
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
	public JSONObject getPayLoad() {
		JSONObject payLoad = new JSONObject();
		setReceipt(payLoad);
		System.out.println("PayLoad " + payLoad.toString());
		return payLoad;
	}

	@Override
	public String getURI() {
		return "/harmoney2/moose-transaction/Trichy";
	}

	@Override
	public void addExtraHeaders(HttpPost request) {
		request.addHeader("X-userId",userName);
		request.addHeader("Cookie","JSESSIONID="+sessionId);
	}
	
	
	
}
