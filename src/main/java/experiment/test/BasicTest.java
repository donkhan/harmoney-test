package experiment.test;

import experiment.authentication.LoginTest;
import experiment.authentication.LogoutTest;
import org.apache.http.HttpResponse;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by kkhan on 07/09/18.
 */
public abstract class BasicTest extends BaseGETTest{


    public void exeuteFullCycle(){
        System.out.println("Logging In...");

        LoginTest test = new LoginTest();
        try {
            String sessionId = test.login();
            System.out.println("Logged In...");
            long l = System.currentTimeMillis();
            super.execute();
            System.out.println("Test takes " + (System.currentTimeMillis() - l) + " msecs");
            System.out.println("");
            System.out.println("Logging Out...");
            LogoutTest logoutTest = new LogoutTest(sessionId);
            logoutTest.execute();
            System.out.println("Logged Out...");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void print(HttpResponse response) throws IOException {
        InputStream is = response.getEntity().getContent();
        int i = -1;
        while(( i = is.read())!= -1){
            System.out.print((char)i);
        }
        System.out.println("");
    }
}
