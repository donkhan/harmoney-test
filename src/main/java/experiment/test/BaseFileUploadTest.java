package experiment.test;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by kkhan on 16/05/19.
 */
public abstract class BaseFileUploadTest extends BaseTest {

    String userName;
    String sessionId;

    public abstract String getURI();

    public BaseFileUploadTest(String userName, String sessionId) {
        this.userName = userName;
        this.sessionId = sessionId;
    }

    private void sendFile( File uploadFile, URL url)
            throws IOException {
        System.out.println("Send File " + uploadFile.getName() + " URL " + url.toExternalForm());
        String charset = "UTF-8";
        HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        httpConn.setUseCaches(false);
        httpConn.setDoOutput(true); // indicates POST method
        httpConn.setDoInput(true);
        httpConn.setRequestProperty("Content-Type",
                "multipart/form-data; boundary=" + boundary);
        httpConn.setRequestProperty("User-Agent", "CodeJava Agent");
        httpConn.setRequestProperty("Test", "Bonjour");
        //httpConn.setRequestMethod("Accept");
        System.out.println("User Name " + userName + " JSESSION ID " + sessionId);
        httpConn.setRequestProperty("X-userId",userName);
        httpConn.setRequestProperty("Cookie","JSESSIONID="+sessionId);
        System.out.println("Request Headers are set");
        OutputStream outputStream = httpConn.getOutputStream();
        PrintWriter writer = new PrintWriter(new OutputStreamWriter(outputStream, charset), true);

        String fileName = uploadFile.getName();
        writer.append("--" + boundary).append(LINE_FEED);
        writer.append(
                "Content-Disposition: form-data; name=\"" + "image"
                        + "\"; filename=\"" + fileName + "\"")
                .append(LINE_FEED);
        writer.append(
                "Content-Type: "
                        + URLConnection.guessContentTypeFromName(fileName))
                .append(LINE_FEED);
        writer.append("Content-Length:" + uploadFile.length());
        writer.append("Content-Transfer-Encoding: binary").append(LINE_FEED);
        writer.append(LINE_FEED);
        writer.flush();
        System.out.println("Content Disposition Transfer Encoding File Name is flushed");

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
        System.out.println("File is flushed");
        outputStream.close();
       // httpConn.disconnect();

    }

    private final String boundary = "===" + System.currentTimeMillis() + "===";
    private static final String LINE_FEED = "\r\n";

    public void execute(File file) throws IOException {
        String requestURL = "http://" + getServer() + ":" + getPort() + getURI();
        URL url = new URL(requestURL);
        System.out.println("URL is " + url);
        sendFile(file,url);
    }
}
