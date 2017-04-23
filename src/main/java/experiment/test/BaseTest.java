package experiment.test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.impl.client.DefaultHttpClient;

import experiment.authentication.LoginTest;
import experiment.authentication.LogoutTest;

public abstract class BaseTest {

	private String server = "localhost";
	private int port = 8181;
	private String userName;
	private String passWord;
	public static String sessionId;
	
	public BaseTest(){
		this.userName = "t";
		this.passWord = "1";
	}
	
	protected String getServer(){
		return server;
	}
	
	protected int getPort(){
		return port;
	}
	
	protected String getUserName(){
		return userName;
	}
	
	protected String getSessionId(){
		return sessionId;
	}

	protected String getPassWord(){
		return passWord;
	}
	
	protected void authenticate() throws ClientProtocolException, IOException{
		LoginTest loginTest = new LoginTest();
		sessionId = loginTest.login();
	}
	
	protected void logout() throws ClientProtocolException, IOException{
		LogoutTest logout = new LogoutTest();
		logout.execute();
	}
	
	public abstract HttpRequestBase getHttpRequest(String url) throws UnsupportedEncodingException;
		
	

	protected HttpResponse execute() throws ClientProtocolException,IOException{
		HttpClient client = new DefaultHttpClient();
		String url = "http://"+getServer()+":"+ getPort() + getURI();
		HttpRequestBase request = getHttpRequest(url);
		addDefaultHeaders(request);
		addExtraHeaders(request);
		HttpResponse response = client.execute(request);
		return response;
	}
	
	public void setContent(HttpRequestBase request) throws UnsupportedEncodingException{
	}
	
	protected void addDefaultHeaders(HttpRequestBase request){
		request.addHeader("Content-Type","application/json;charset=UTF-8");
		request.addHeader("User-Agent", "STANDALONE_CODE");
		request.addHeader("Accept","application/json");
		request.addHeader("X-userId",userName);
		request.addHeader("Cookie","JSESSIONID="+sessionId);
	}
	
	protected void executeAndPrint() throws IllegalStateException, ClientProtocolException, IOException{
		process(execute());
	}
	
	protected void process(HttpResponse response) throws IllegalStateException, IOException{
		BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
		String s = "";
		while((s = reader.readLine()) != null){
			System.out.println(s);
		}
		reader.close();
	}
	
	
	public abstract String getURI();
	public void addExtraHeaders(HttpRequestBase request){
	}
	
	public void oneCycle() throws ClientProtocolException, IOException{
		authenticate();
		process(execute());
		logout();
		
	}
}
