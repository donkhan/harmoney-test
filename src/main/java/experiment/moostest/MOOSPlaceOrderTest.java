package experiment.moostest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONObject;

import experiment.moostest.model.MOOSOrder;
import experiment.test.BasePostTest;

public class MOOSPlaceOrderTest extends BasePostTest{
	
	private List<MOOSOrder> transactions;
	private String branchName;
	
	public List<MOOSOrder> getTransactions() {
		return transactions;
	}
	public void setTransactions(List<MOOSOrder> transactions) {
		this.transactions = transactions;
	}
	public String getBranchName() {
		return branchName;
	}
	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}
	public static void main(String args[]) throws ClientProtocolException, IOException{
		List<MOOSOrder> list = new ArrayList<MOOSOrder>();
		list.add(new MOOSOrder("USD",10,1));
		list.add(new MOOSOrder("GBP",10,5));
		MOOSPlaceOrderTest dt = new MOOSPlaceOrderTest(list);
		dt.setBranchName("MADURAI");
		dt.oneCycle();
		
	}
	public MOOSPlaceOrderTest(List<MOOSOrder> transactions){
		this.transactions = transactions;
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
}
