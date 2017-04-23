package experiment.test;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;

public abstract class BaseGETTest extends BaseTest{
	@Override
	public HttpRequestBase getHttpRequest(String url) {
		HttpGet request = new HttpGet(url);
		return request;
	}
	
}
