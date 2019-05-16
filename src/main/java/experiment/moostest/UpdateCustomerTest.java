package experiment.moostest;

import experiment.authentication.LoginTest;
import experiment.authentication.LogoutTest;

import experiment.test.BasePUTTest;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPut;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by kkhan on 16/05/19.
 */
public class UpdateCustomerTest extends BasePUTTest {


    String userName;
    String sessionId;

    public UpdateCustomerTest(String userName, String sessionId) {
        this.userName = userName;
        this.sessionId = sessionId;
    }

    public static void main(String args[]) {
        String userName = "sadmin";
        String passWord = "A123456*";

        LoginTest loginTest = new LoginTest(userName, passWord);
        String sessionId = "";
        try {
            sessionId = loginTest.login();
            UpdateCustomerTest dt = new UpdateCustomerTest(userName, sessionId);
            HttpResponse response = dt.execute();
            BufferedReader rd = new BufferedReader(
                    new InputStreamReader(response.getEntity().getContent()));

            StringBuffer result = new StringBuffer();
            String line = "";
            while ((line = rd.readLine()) != null) {
                result.append(line);
            }
            System.out.println(result);

        } catch (Throwable e) {
            e.printStackTrace();
        } finally {
            LogoutTest logout = new LogoutTest(userName, sessionId);
            try {
                logout.execute();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public String getPayLoad() {
        JSONObject payLoad = new JSONObject();
        payLoad.put("accountId", 1739);
        payLoad.put("address", "E323 Wilson Street");
        payLoad.put("cityCode", "C");
        payLoad.put("countryCode", "Malaysia");
        payLoad.put("dob", "2000-04-04T17:16:32.227Z");
        payLoad.put("emailId", "a@a.com");
        payLoad.put("mobileNo", "12323");
        payLoad.put("firstName", "NURUL NABILA BINTI HAJI ROSLAN");
        payLoad.put("gender", "F");
        payLoad.put("identityCardNumber", "871019055110");
        payLoad.put("id", 1176);
        payLoad.put("lastName", "l1");
        payLoad.put("maritalStatus", "M");
        payLoad.put("nationality", "Malaysia");
        payLoad.put("occupation", "Doctor");
        payLoad.put("ofac", false);
        payLoad.put("pbl", false);
        payLoad.put("pep", false);
        payLoad.put("postalCode", "1212");
        payLoad.put("stateCode", "D");
        payLoad.put("resident", "Y");
        System.out.println("PayLoad " + payLoad.toString());
        return payLoad.toString();
    }

    @Override
    public String getURI() {
        return "/harmoney2/customers/customer";
    }

    @Override
    public void addExtraHeaders(HttpPut request) {
        request.addHeader("X-userId", userName);
        request.addHeader("Cookie", "JSESSIONID=" + sessionId);
    }
}


