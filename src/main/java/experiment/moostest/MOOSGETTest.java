package experiment.moostest;
import experiment.test.BaseGETTest;

public class MOOSGETTest extends BaseGETTest{

	private String uri;
	@Override
	public String getURI() {
		return uri;
	}
	
	public void setURI(String s){
		this.uri = s;
	}
	
}
