package experiment.moneyboxtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.json.JSONArray;
import org.json.JSONObject;

import experiment.moneyboxtest.model.Transaction;
import experiment.test.BasePOSTTest;

public class TransactionTest extends BasePOSTTest {
	
	private List<Transaction> transactions;
	private String userName;
	private String sessionId;
	
	public static void main(String args[]) throws ClientProtocolException, IOException{
		
	}
	
	public TransactionTest(List<Transaction> transactions,String userName,String sessionId){
		this.transactions = transactions;
		this.userName = userName;
		this.sessionId = sessionId;
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
		setRiskAnalysis(payLoad);
		return payLoad.toString();
	}

	@Override
	public String getURI() {
		return "/harmoney2/tranReceiptCounters/tranReceiptCounter";
	}

	@Override
	public void addExtraHeaders(HttpPost request) {
		request.addHeader("X-userId",userName);
		request.addHeader("Cookie","JSESSIONID="+sessionId);
	}
	
	
	
}
