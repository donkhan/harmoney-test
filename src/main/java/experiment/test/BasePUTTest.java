package experiment.test;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.DefaultHttpClient;
import java.io.*;
import java.net.*;

public abstract class BasePUTTest extends BaseTest{



    public abstract String getPayLoad();
    public abstract String getURI();
    public abstract void addExtraHeaders(HttpPost request);
}
