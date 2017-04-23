package experiment.moostest;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;

import experiment.test.BaseGETTest;

public class FindCustomerTest extends BaseGETTest{

	public static void main(String args[]) throws ClientProtocolException, IOException{
		FindCustomerTest stest = new FindCustomerTest();
		stest.oneCycle();
	}

	@Override
	public String getURI() {
		return "/harmoney2/customers/findByIdentificationNumber/I1";
	}
}
