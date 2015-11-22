package experiment;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONObject;

public class TransactionTest {
	
	private List<Transaction> transactions;
	public static void main(String args[]) throws ClientProtocolException, IOException{
		
	}
	
	public TransactionTest(List<Transaction> transactions){
		this.transactions = transactions;
	}
	
	private void setReceipt(JSONObject request){
		double t = setTransactions(request);
		request.put("totalAmount",t);
		request.put("customerAmount", 0d);
		request.put("balanceAmount", t);
		request.put("accountId", 0);
		request.put("purpose", "");
		request.put("description", "");
		request.put("totalSaleAmount", t);
		
	}
	
	private void setRiskAnalysis(JSONObject request){
		JSONObject riskAnalysis = new JSONObject();
		riskAnalysis.put("state", 0);
		riskAnalysis.put("id", 0);
		riskAnalysis.put("status", "");
		riskAnalysis.put("description","");
		riskAnalysis.put("points", 0);
		riskAnalysis.accumulate("transactionDate", null);
		riskAnalysis.accumulate("customerId", 0);
		riskAnalysis.accumulate("date", 0);
		riskAnalysis.accumulate("receiptId", 0);
		riskAnalysis.accumulate("createTime", null);
		riskAnalysis.accumulate("updateTime", null);
		riskAnalysis.accumulate("createBy", null);
		riskAnalysis.accumulate("updateBy", null);
		riskAnalysis.accumulate("message", null);
		request.put("riskAnalysis", riskAnalysis);
	}
	
	private double setTransactions(JSONObject request){
		JSONObject tran = new JSONObject();
		double total = 0d;
		JSONArray atransactions = new JSONArray();
		for(Transaction orig : transactions){
			tran.put("exchangeRateId",orig.getExchangeRateId());
			tran.put("currencyId",orig.getCurrencyId());
			tran.put("type",orig.getType());
			tran.put("baseUnit",orig.getBaseUnit());
			tran.put("exchangeUnit",orig.getExchangeUnit());
			tran.put("exchangeRate",orig.getExchangeRate());
			double x = orig.getExchangeRate() * orig.getExchangeUnit();
			total += x;
			tran.put("amount",x);
			atransactions.put(tran);
		}
		request.put("transactions",atransactions);
		return total;
	}
	
	public void execute(String sessionId) throws ClientProtocolException, IOException{
		
		JSONObject payLoad = new JSONObject();
		setReceipt(payLoad);
		setRiskAnalysis(payLoad);
		
		System.out.println(payLoad.toString(2));
		
		String url = "http://localhost:8181//harmoney2/tranReceiptCounters/tranReceiptCounter";

		HttpClient client = new DefaultHttpClient();
		HttpPost request = new HttpPost(url);
		
		request.addHeader("Content-Type","application/json;charset=UTF-8");
		request.addHeader("User-Agent", "STANDALONE_CODE");
		request.addHeader("Accept","application/json");
		request.addHeader("X-userId","vteial");
		request.addHeader("Cookie","JSESSIONID="+sessionId);
		
		System.out.println("Session ID " + sessionId);
		
		//{"totalAmount":-3.24,"customerAmount":0,"balanceAmount":3.24,"accountId":0,"purpose":"","description":"","transactions":[{"exchangeRateId":52,"currencyId":"USD","baseUnit":1,"type":"B","exchangeUnit":1,"exchangeRate":3.24,"amount":3.24}],"totalSaleAmount":3.24}
		
		String counterPayLoad = payLoad.toString(2);
		
		request.setEntity(new StringEntity(counterPayLoad));
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
		System.out.println(result);

	}
	
	
}
