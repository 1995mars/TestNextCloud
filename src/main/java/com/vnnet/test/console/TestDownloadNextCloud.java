package com.vnnet.test.console;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.commons.httpclient.methods.GetMethod;
import org.apache.commons.io.IOUtils;

import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TestDownloadNextCloud {
    public static void main(String[] args) {


        //        boolean useHTTPS = true;
        //        int port = 443;
        //        String userName = "nc_admin";
        //        String password = "nextcloudpassword";
        //
        //        NextcloudConnector nxt = new NextcloudConnector(serverName, useHTTPS, port, userName, password);
        //        nxt.createUser("test2","test2");
        try {

            final String baseUrl = "https://tung95.duckdns.org//remote.php/webdav";
            try {
                HttpClient client = new HttpClient();
                Credentials creds = new UsernamePasswordCredentials("nc_admin", "nextcloudpassword");
                client.getState().setCredentials(AuthScope.ANY, creds);
                GetMethod method = new GetMethod(baseUrl + "/" + "Photos/Coast1.jpg");


                //                HttpEntity entity = method. .getEntity();
                //                HttpResponse response = client.
                client.executeMethod(method);
//                System.out.println(method.getStatusCode() + " " + method.getStatusText());
                InputStream inputStream = method.getResponseBodyAsStream();
                FileOutputStream fileOS = new FileOutputStream("/home/t94hp/Desktop/Coa.jpg");
                int i = IOUtils.copy(inputStream, fileOS);

            } catch (HttpException ex) {
            }
        } catch (Throwable ex) {
            Logger.getLogger(TestDownloadNextCloud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}