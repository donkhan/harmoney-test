package experiment.test;

/**
 * Created by kkhan on 06/09/18.
 */
public abstract class BaseTest {
    private String server = "210.19.94.136";
    private int port = 8181;

    protected String getServer(){
        return server;
    }

    protected int getPort(){
        return port;
    }
}
