package experiment;


public class MooseGETTest extends BaseGETTest{

	private String uri;
	
	public MooseGETTest(String userName,String sessionId){
		super(userName,sessionId);
	}
	
	@Override
	public String getURI() {
		return uri;
	}
	
	public void setURI(String s){
		this.uri = s;
	}
}
