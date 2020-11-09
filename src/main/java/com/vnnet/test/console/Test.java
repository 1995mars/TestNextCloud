package com.vnnet.test.console;

import org.aarboard.nextcloud.api.*;
import org.aarboard.nextcloud.api.filesharing.FilesharingConnector;
import org.aarboard.nextcloud.api.filesharing.Share;
import org.aarboard.nextcloud.api.filesharing.SharePermissions;
import org.aarboard.nextcloud.api.filesharing.ShareType;
import org.aarboard.nextcloud.api.provisioning.ShareData;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Optional;

public class Test {

    private static final String TEST_FOLDER = "/Musis/cogiao1.jpg";
    private static final String TESTUSER = "/nc_admin";

    public static void main(String[] args) {

        try {
            NextcloudConnector nextcloudConnector = new NextcloudConnector("tung95.duckdns.org", true, 443, "nc_admin", "nextcloudpassword");

            // // download file vve may
//            nextcloudConnector.downloadFile("/Photos/Coast1.jpg","/home/t94hp/Desktop/");
            /// // Kiem tra thu muc da duoc tao hay chua
            boolean kq_check =  nextcloudConnector.fileExists("PrivateKey/next1/rsa_private.pem");

            if (kq_check == false) {
                nextcloudConnector.createFolder("PrivateKey/tung-iot");
            }
            System.out.println(kq_check);
            // // upload file len he thong
//            InputStream initialStream = new FileInputStream(new File("/home/t94hp/Desktop/cogiao.jpg"));
//            nextcloudConnector.uploadFile(initialStream,"/Musics/cogiao1.jpg");
            // //Tao 1 user moi
//            boolean kq = nextcloudConnector.createUser("qmstung","qmstung");
//            System.out.println();
            // // Tao moi thu muc cho quan tri vien
//            nextcloudConnector.crzaloeateFolder("Musics");
//            nextcloudConnector.deleteFolder("Musics");
            // // Tao 1 tai khoan moi
//            boolean result = nextcloudConnector.createUser("TESTUSER", "aBcDeFg123456");
             // Chia se file public
//            SharePermissions permissions = new SharePermissions(SharePermissions.SingleRight.READ);
//            Share result = nextcloudConnector.doShare("/Photos/cogiao.jpg", ShareType.PUBLIC_LINK,null,null,null,permissions);
//            System.out.println(result.getUrl());
//            System.out.println(result.getId());
//            System.out.println("Thoi gian");
//            SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//            Date date = new Date(System.currentTimeMillis());
//
//            Calendar c1 = Calendar.getInstance();
//            c1.setTime(date);
//
//            c1.add(Calendar.HOUR, 2);
//            System.out.println(formatter.format(c1.getTime()));
//
//            ServerConfig serverConfig = new ServerConfig("tung95.duckdns.org",true, 443,"nc_admin", "nextcloudpassword");
//            FilesharingConnector instance = new FilesharingConnector(serverConfig);
//            boolean result = nextcloudConnector.editShare(8, ShareData.EXPIREDATE, formatter.format(c1.getTime()));
//
//           boolean kq = nextcloudConnector.editShare(8, ShareData.EXPIREDATE,"2020-07-24");

            // // kiem tra thong tin file chia se
//            Share share = nextcloudConnector.getShareInfo(8);
//            System.out.println(share.getExpiration());
//            // // Huy chia se link file
//            boolean kq = nextcloudConnector.deleteShare(7);
//            System.out.println(result);
            // // disableUser User
//            nextcloudConnector.disableUser("TESTUSER");
//            nextcloudConnector.enableUser("TESTUSER");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Optional<Share> findShare(FilesharingConnector instance) {
        return instance.getShares(TEST_FOLDER, false, false).stream()
                .filter(s -> TEST_FOLDER.equals(s.getPath()) && TESTUSER.equals(s.getShareWithId())).findFirst();
    }

}