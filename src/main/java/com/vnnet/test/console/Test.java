package com.vnnet.test.console;

import org.aarboard.nextcloud.api.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

public class Test {
    public static void main(String[] args) {
        final String baseUrl = "https://tung95.duckdns.org//remote.php/webdav";
        try {
            NextcloudConnector nextcloudConnector = new NextcloudConnector("tung95.duckdns.org", true, 443, "nc_admin", "nextcloudpassword");
            // download file vve may
//            nextcloudConnector.downloadFile("/Photos/Coast1.jpg","/home/t94hp/Desktop/");
            // upload file len he thong
//            InputStream initialStream = new FileInputStream(new File("/home/t94hp/Desktop/cogiao.jpg"));
//            nextcloudConnector.uploadFile(initialStream,"/Photos/cogiao.jpg");
            //Tao 1 user moi
//            boolean kq = nextcloudConnector.createUser("qmstung","qmstung");
//            System.out.println();
            //Tao moi thu muc cho quan tri vien
//            nextcloudConnector.createFolder("Musics");
//            nextcloudConnector.deleteFolder("Musics");
            boolean result = nextcloudConnector.createUser("TESTUSER", "aBcDeFg123456");

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}