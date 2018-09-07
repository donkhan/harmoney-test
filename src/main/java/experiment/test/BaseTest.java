package experiment.test;

/**
 * Created by kkhan on 06/09/18.
 */
public abstract class BaseTest {
    private String server = "101.99.73.46";
    private int port = 9181;

    protected String getServer(){
        return server;
    }

    protected int getPort(){
        return port;
    }
}
