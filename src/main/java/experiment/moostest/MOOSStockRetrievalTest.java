package experiment.moostest;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import experiment.test.BaseGETTest;

public class MOOSStockRetrievalTest extends BaseGETTest{
	
	private String uri;
	
	public MOOSStockRetrievalTest(){
	}
	
	@Override
	public String getURI() {
		return uri;
	}
	
	public void setURI(String s){
		this.uri = s;
	}
	
	public static void main(String args[]) throws ClientProtocolException, IOException{
		MOOSStockRetrievalTest stest = new MOOSStockRetrievalTest();
		stest.authenticate();
		stest.setURI("/harmoney2/moos/get-stocks/Trichy/USD");
		stest.execute();
		System.out.println("");
		stest.setURI("/harmoney2/moos/get-stocks/Trichy/ALL");
		stest.execute();
		System.out.println("");
		stest.setURI("/harmoney2/moos/get-stocks/Trichy/USE");
		stest.execute();
		System.out.println("");
		stest.setURI("/harmoney2/moos/get-stocks/Trichi/USD");
		stest.execute();
		System.out.println("");
		stest.logout();	
	}

}
