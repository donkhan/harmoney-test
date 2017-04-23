package experiment.authentication;

import experiment.test.BaseGETTest;

public class LogoutTest extends BaseGETTest{

	@Override
	public String getURI() {
		return "/harmoney2/sessionService/logout";
	}
}
