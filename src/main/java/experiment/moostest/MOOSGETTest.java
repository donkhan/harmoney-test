package experiment;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpResponse;


public class MOOSGETTest extends BaseGETTest{

	private String uri;
	
	public MOOSGETTest(String userName,String sessionId){
		super(userName,sessionId);
	}
	
	@Override
	public String getURI() {
		return uri;
	}
	
	public void setURI(String s){
		this.uri = s;
	}
	
	public void print(HttpResponse response) throws IOException{
		InputStream is = response.getEntity().getContent();
		int j;
		while(( j = is.read()) != -1){
			System.out.print((char)j);
		}
	}
}
