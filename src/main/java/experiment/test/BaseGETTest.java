package experiment.test;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

public abstract class BaseGETTest  extends BaseTest{

	public HttpResponse execute() throws ClientProtocolException,
			IOException {
		HttpClient client = new DefaultHttpClient();
		String url = "http://" + getServer() + ":" + getPort() + getURI();
		System.out.println("URL " + url);
		HttpGet request = new HttpGet(url);
		request.addHeader("Content-Type", "application/json;charset=UTF-8");
		request.addHeader("User-Agent", "STANDALONE_CODE");
		request.addHeader("Accept", "application/json");
		addExtraHeaders(request);
		HttpResponse response = client.execute(request);
		print(response);
		return response;
	}

	public abstract String getURI();

	private String sessionId;
	
	public BaseGETTest(String userName,String sessionId){
		setUserName(userName);
		this.sessionId = sessionId;
	}

	public BaseGETTest(String sessionId){
		this.sessionId = sessionId;
	}

	public BaseGETTest(){
	}

	public String getSessionId() {
		return sessionId;
	}

	public void addExtraHeaders(HttpGet request){
		request.addHeader("X-userId",getUserName());
		request.addHeader("Cookie","JSESSIONID="+sessionId);
	}
	
	public abstract void print(HttpResponse response) throws IOException;
}
