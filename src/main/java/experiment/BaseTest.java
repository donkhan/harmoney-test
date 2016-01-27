package experiment;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONObject;

public abstract class BaseTest {

	//private String server = "101.99.73.46";
	private String server = "localhost";
	private int port = 8181;
	
	protected String getServer(){
		return server;
	}
	
	protected int getPort(){
		return port;
	}
	
	protected HttpResponse execute() throws ClientProtocolException, IOException{
		HttpClient client = new DefaultHttpClient();
		String url = "http://"+getServer()+":"+ getPort() + getURI();
		HttpPost request = new HttpPost(url);

		
		request.addHeader("Content-Type","application/json;charset=UTF-8");
		request.addHeader("User-Agent", "STANDALONE_CODE");
		request.addHeader("Accept","application/json");
		addExtraHeaders(request);
		
		String content = getPayLoad().toString();
		
		request.setEntity(new StringEntity(content));
		HttpResponse response = client.execute(request);
		return response;
	}
	
	public abstract JSONObject getPayLoad();
	public abstract String getURI();
	public abstract void addExtraHeaders(HttpPost request);
}
