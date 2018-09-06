package experiment.moostest;

import experiment.authentication.LoginTest;
import experiment.authentication.LogoutTest;
import experiment.test.BaseGETTest;
import org.apache.http.HttpResponse;

import java.io.IOException;
import java.io.InputStream;

/**
 * Created by kkhan on 06/09/18.
 */
public class FindCustomerRiskProfileTest extends BaseGETTest{

    public FindCustomerRiskProfileTest(String userName, String sessionId) {
        super(userName, sessionId);
    }

    public static void main(String args[]){
        String userName = "sadmin";
        String passWord = "A123456*";

        LoginTest test = new LoginTest(userName,passWord);
        try {
            String sessionId = test.login();
            FindCustomerRiskProfileTest stest = new FindCustomerRiskProfileTest(userName,sessionId);
            stest.execute();
            System.out.println("");
            LogoutTest logoutTest = new LogoutTest(userName,sessionId);
            logoutTest.execute();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private String idNo = "920519016204";

    @Override
    public String getURI() {
        return "/harmoney2/moos/customer/"+idNo+"/get-risk-profile";
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
