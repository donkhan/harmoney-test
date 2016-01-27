package experiment;

import java.io.IOException;
import java.io.InputStream;

import org.apache.http.HttpResponse;


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
	
	public void print(HttpResponse response) throws IOException{
		System.out.println(response.getStatusLine().getStatusCode());
		InputStream is = response.getEntity().getContent();
		int j;
		while(( j = is.read()) != -1){
			System.out.print((char)j);
		}
	}
}
