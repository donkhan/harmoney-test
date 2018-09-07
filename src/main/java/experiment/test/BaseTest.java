package experiment.test;

/**
 * Created by kkhan on 06/09/18.
 */
public abstract class BaseTest {
    private String server = "101.99.73.46";
    private int port = 9181;

    String userName = "sadmin";
    String passWord = "A123456*";

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    protected String getServer(){
        return server;
    }

    protected int getPort(){
        return port;
    }
}
