package experiment.test;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPut;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;

import java.io.IOException;

public abstract class BasePUTTest extends BaseTest {


    public abstract String getPayLoad();

    public abstract String getURI();

    public abstract void addExtraHeaders(HttpPut request);

    public HttpResponse execute() throws ClientProtocolException, IOException {
        HttpClient client = new DefaultHttpClient();
        String url = "http://"+getServer()+":"+ getPort() + getURI();
        HttpPut request = new HttpPut(url);


        request.addHeader("Content-Type","application/json;charset=UTF-8");
        request.addHeader("User-Agent", "STANDALONE_CODE");
        request.addHeader("Accept","application/json");
        addExtraHeaders(request);

        String content = getPayLoad();
        request.setEntity(new StringEntity(content));
        HttpResponse response = client.execute(request);
        System.out.println("Got Response...");
        return response;
    }
}
