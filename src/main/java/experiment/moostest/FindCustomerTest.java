package experiment.moostest;

import experiment.test.BasicTest;

public class FindCustomerTest extends BasicTest{

	public static void main(String args[]) {
        new FindCustomerTest().exeuteFullCycle();
    }

	private String icNo = "920519016204";

	@Override
	public String getURI() {
		return "/harmoney2/customers/findByIdentificationNumber/" + icNo;
	}
}
