package experiment.test;

import java.io.UnsupportedEncodingException;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;

public abstract class BasePostTest extends BaseTest{
	@Override
	public HttpRequestBase getHttpRequest(String url) throws UnsupportedEncodingException {
		HttpPost request = new HttpPost(url);
		setContent(request);
		return request;
	}
	public abstract String getPayLoad();
	public void setContent(HttpRequestBase request) throws UnsupportedEncodingException{
		HttpPost post = (HttpPost) request;
		String content = getPayLoad();
		post.setEntity(new StringEntity(content));
	}
}
