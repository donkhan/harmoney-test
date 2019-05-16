package experiment.test;

import org.apache.http.client.methods.HttpPost;

public abstract class BasePUTTest extends BaseTest {


    public abstract String getPayLoad();

    public abstract String getURI();

    public abstract void addExtraHeaders(HttpPost request);
}
