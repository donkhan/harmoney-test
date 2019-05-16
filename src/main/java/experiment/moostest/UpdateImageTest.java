package experiment.moostest;

import experiment.authentication.LoginTest;
import experiment.authentication.LogoutTest;
import experiment.test.BaseFileUploadTest;

import java.io.IOException;

/**
 * Created by kkhan on 15/05/19.
 */
public class UpdateImageTest extends BaseFileUploadTest {


    public String getURI() {
        String id = "1234";
        return "/harmoney2/customers/customer/" + id + "/icFrontImage";
    }

    public UpdateImageTest(String userName, String sessionId) {
        super(userName, sessionId);
    }

    public static void main(String args[]) {
        String userName = "sadmin";
        String passWord = "A123456*";
        System.out.println("Started P....");
        LoginTest loginTest = new LoginTest(userName, passWord);
        String sessionId = "";
        try {
            sessionId = loginTest.login();
            System.out.println("Logged In with session Id " + sessionId);
            UpdateImageTest dt = new UpdateImageTest(userName, sessionId);
            dt.execute();
        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            LogoutTest logout = new LogoutTest(userName, sessionId);
            try {
                logout.execute();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


}
