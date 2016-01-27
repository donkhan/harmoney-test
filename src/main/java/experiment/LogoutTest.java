package experiment;


public class LogoutTest extends BaseGETTest{

	@Override
	public String getURI() {
		return "/harmoney2/sessionService/logout";
	}
	
	public LogoutTest(String userName,String sessionId){
		super(userName,sessionId);
	}

	
	
}
