package com.vnnet.test.console;

import org.aarboard.nextcloud.api.*;
import org.apache.commons.io.IOUtils;

import java.io.*;

public class Test {
    public static void main(String[] args) {
        final String baseUrl = "https://tung95.duckdns.org//remote.php/webdav";
        try {
            NextcloudConnector nextcloudConnector = new NextcloudConnector("tung95.duckdns.org", true, 443, "nc_admin", "nextcloudpassword");
            InputStream initialStream = new FileInputStream(
                    new File("/home/t94hp/Desktop/Coast.jpg"));
//            nextcloudConnector.uploadFile( initialStream,"/Photos/Coast2.jpg");
//            nextcloudConnector.downloadFile("/Photos/Coast1.jpg","/home/t94hp/Desktop/");
            nextcloudConnector.createFolder("nc_admin/Musics");
//            InputStream inputStream  = nextcloudConnector.downloadFile("/Photos/Coast1.jpg");
//            String fileName = "test.jpg";
//            FileOutputStream fos = new FileOutputStream(fileName);
//            int i = IOUtils.copy(inputStream, fos);
//            System.out.println(i);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}