package experiment.moneyboxtest;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import experiment.authentication.LogoutTest;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;
import org.json.JSONArray;
import org.json.JSONObject;

import experiment.authentication.LoginTest;
import experiment.moneyboxtest.model.Transaction;
import experiment.test.BasePOSTTest;

public class ValidateRateTest extends BasePOSTTest {
	
	private List<Transaction> transactions;
	private String userName;
	private String sessionId;

	private void testProper(String userName,String sessionId) throws ClientProtocolException,IOException{
		List<Transaction> l = new ArrayList<Transaction>();
		l.add(new Transaction("B",25419,"GBP",1,5.4,1));
		ValidateRateTest vrt = new ValidateRateTest();
		vrt.transactions = l;
		vrt.userName = userName;
		vrt.sessionId = sessionId;
		vrt.executeTransactions();
	}

	private void testRateZero(String userName,String sessionId) throws ClientProtocolException,IOException{
		List<Transaction> l = new ArrayList<Transaction>();
		l.add(new Transaction("B",25419,"GBP",1,0,1));
		ValidateRateTest vrt = new ValidateRateTest();
		vrt.transactions = l;
		vrt.userName = userName;
		vrt.sessionId = sessionId;
		vrt.executeTransactions();
	}

	private void testRateExceed(String userName,String sessionId) throws ClientProtocolException,IOException{
		List<Transaction> l = new ArrayList<Transaction>();
		l.add(new Transaction("B",25419,"GBP",1,6,1));
		ValidateRateTest vrt = new ValidateRateTest();
		vrt.transactions = l;
		vrt.userName = userName;
		vrt.sessionId = sessionId;
		vrt.executeTransactions();
	}

	public static void main(String args[]) throws ClientProtocolException, IOException{
		String userName = "teller";
		String passWord = "A123456*";
		LoginTest login = new LoginTest(userName,passWord);
		String sessionId = login.login();
		ValidateRateTest vrt = new ValidateRateTest();

		vrt.testProper(userName,sessionId);
		vrt.testRateZero(userName,sessionId);
		vrt.testRateExceed(userName,sessionId);

		LogoutTest logout = new LogoutTest(userName,sessionId);
		logout.execute();
	}
	
	public ValidateRateTest() {
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
