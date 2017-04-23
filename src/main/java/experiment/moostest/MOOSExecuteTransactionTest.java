package experiment.moostest;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONObject;

import experiment.moostest.model.MOOSOrder;
import experiment.test.BasePostTest;

public class MOOSExecuteTransactionTest extends BasePostTest{
	
	private List<MOOSOrder> transactions;
	public static void main(String args[]) throws ClientProtocolException, IOException{
		List<MOOSOrder> list = new ArrayList<MOOSOrder>();
		list.add(new MOOSOrder("USD",10,1));
		list.add(new MOOSOrder("GBP",10,5));
		MOOSExecuteTransactionTest dt = new MOOSExecuteTransactionTest(list);
		dt.oneCycle();
	}

	public MOOSExecuteTransactionTest(List<MOOSOrder> transactions){
		this.transactions = transactions;
	}
	
	private void setReceipt(JSONObject request){
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
		payLoad.put("branch-name", "Trichy");
		System.out.println("PayLoad " + payLoad.toString());
		return payLoad.toString();
	}

	@Override
	public String getURI() {
		return "/harmoney2/moos/execute-transactions";
	}
}
