package experiment.moostest;

import experiment.authentication.LoginTest;
import experiment.authentication.LogoutTest;
import experiment.test.BasePUTTest;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpPost;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by kkhan on 15/05/19.
 */
public class UpdateImageTest extends BasePUTTest {

    @Override
    public String getPayLoad() {
        return null;
    }

    @Override
    public String getURI() {
        String id = "1234";
        return "/harmoney2/customers/customer/" + id + "/icFrontImage";
    }

    @Override
    public void addExtraHeaders(HttpPost request) {
        request.addHeader("X-userId",userName);
        request.addHeader("Cookie","JSESSIONID="+sessionId);
    }

    String userName;
    String sessionId;

    public UpdateImageTest(String userName, String sessionId) {
        this.userName = userName;
        this.sessionId = sessionId;
    }

    public static void main(String args[]){
        String userName = "sadmin";
        String passWord = "A123456*";

        LoginTest loginTest = new LoginTest(userName,passWord);
        String sessionId = "";
        try {
            sessionId = loginTest.login();
            UpdateImageTest dt = new UpdateImageTest(userName,sessionId);
            dt.execute();
        } catch (Throwable e) {
            e.printStackTrace();
        } finally{
            LogoutTest logout = new LogoutTest(userName,sessionId);
            try {
                logout.execute();
            } catch (ClientProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private final String boundary = "===" + System.currentTimeMillis() + "===";;
    private static final String LINE_FEED = "\r\n";

    public void execute() throws ClientProtocolException, IOException {
        String requestURL = "http://" + getServer() + ":" + getPort() + getURI();
        URL url = new URL(requestURL);
        sendFile("File",new File("/tmp/a.jpg"),url);
    }

    private void sendFile(String fieldName, File uploadFile, URL url)
            throws IOException {
        String charset = "UTF-8";
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setUseCaches(false);
        httpConn.setDoOutput(true); // indicates POST method
        httpConn.setDoInput(true);
        httpConn.setRequestProperty("Content-Type",
                "multipart/form-data; boundary=" + boundary);
        httpConn.setRequestProperty("User-Agent", "CodeJava Agent");
        httpConn.setRequestProperty("Test", "Bonjour");
        httpConn.setRequestMethod("Accept");
        httpConn.setRequestProperty("X-userId",userName);
        httpConn.setRequestProperty("Cookie","JSESSIONID="+sessionId);
        OutputStream outputStream = httpConn.getOutputStream();
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream, charset),
                true);

        String fileName = uploadFile.getName();
        writer.append("--" + boundary).append(LINE_FEED);
        writer.append(
                "Content-Disposition: form-data; name=\"" + fieldName
                        + "\"; filename=\"" + fileName + "\"")
                .append(LINE_FEED);
        writer.append(
                "Content-Type: "
                        + URLConnection.guessContentTypeFromName(fileName))
                .append(LINE_FEED);
        writer.append("Content-Transfer-Encoding: binary").append(LINE_FEED);
        writer.append(LINE_FEED);
        writer.flush();

        FileInputStream inputStream = new FileInputStream(uploadFile);
        byte[] buffer = new byte[4096];
        int bytesRead = -1;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
        outputStream.flush();
        inputStream.close();

        writer.append(LINE_FEED);
        writer.flush();
    }

}
