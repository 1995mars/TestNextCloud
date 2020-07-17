package com.vnnet.test.console;

import java.io.File;
import java.io.FileInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.apache.commons.httpclient.Credentials;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.UsernamePasswordCredentials;
import org.apache.commons.httpclient.auth.AuthScope;
import org.apache.jackrabbit.webdav.client.methods.PutMethod;

public class TestNextCloud {
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
                PutMethod method = new PutMethod(baseUrl + "/" + "Photos/Coast1.jpg");
                RequestEntity requestEntity = new InputStreamRequestEntity(
                        new FileInputStream(new File("/home/t94hp/Desktop/Coast.jpg")));
                method.setRequestEntity(requestEntity);
                client.executeMethod(method);
                System.out.println(method.getStatusCode() + " " + method.getStatusText());
            } catch (HttpException ex) {
            }
        } catch (Throwable ex) {
            Logger.getLogger(TestNextCloud.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}