package experiment.moostest;
import java.io.IOException;
import org.apache.http.client.ClientProtocolException;
import experiment.test.BaseGETTest;

public class MOOSCustomerTranTest extends BaseGETTest{
	
	private String uri;
	
	@Override
	public String getURI() {
		return uri;
	}
	
	public void setURI(String s){
		this.uri = s;
	}
	
	public static void main(String args[]) throws ClientProtocolException, IOException{
		MOOSCustomerTranTest stest = new MOOSCustomerTranTest();
		stest.setURI("/harmoney2/moos/customer/IC114/get-risk-profile");
		stest.oneCycle();
	}
}
