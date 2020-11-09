package com.vnnet.test.console;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import org.jaudiotagger.audio.AudioFile;
import org.jaudiotagger.audio.AudioFileIO;
import org.jaudiotagger.audio.AudioHeader;

import junit.framework.Assert;

import org.jaudiotagger.audio.exceptions.CannotReadException;
import org.jaudiotagger.audio.exceptions.InvalidAudioFrameException;
import org.jaudiotagger.audio.exceptions.ReadOnlyFileException;
import org.jaudiotagger.tag.TagException;
import org.junit.Test;

public class Split {
    public static void main(String[] args) throws IOException, NoSuchAlgorithmException, TagException, ReadOnlyFileException, CannotReadException, InvalidAudioFrameException {

        String wavFile1 = "C://mp3//soundtrack.mp3";
        String wavFile2 = "D://playmucic_1604030813962_split_1.mp3";
        FileInputStream fistream1 = new FileInputStream(wavFile1);  // first source file
        FileInputStream fistream2 = new FileInputStream(wavFile2);//second source file
        SequenceInputStream sistream = new SequenceInputStream(fistream1, fistream2);
        FileOutputStream fostream = new FileOutputStream("D://merge1.mp3");//destinationfile

        int temp;

        while( ( temp = sistream.read() ) != -1)
        {
            // System.out.print( (char) temp ); // to print at DOS prompt
            fostream.write(temp);   // to write to file
        }
        fostream.close();
        sistream.close();
        fistream1.close();
        fistream2.close();

//        InputStream inputStream = new FileInputStream("D:\\playmucic_1595479766819.mp3");
//        ByteArrayOutputStream os = new ByteArrayOutputStream();
//        int b;
//        while ((b = inputStream.read()) != -1)
//            os.write(b);
//        double fileByte = os.size()/1024;
//        double fileSize =  fileByte/1024;
//        fileSize = Double.parseDouble(String.format("%.2f",fileSize));
//        System.out.println(fileSize);


//        File target = new File("D:\\playmucic_1595479766819.mp3");
//        double megabytes = ((double)target.length() / 1048576);
//        System.out.println(megabytes);
//        AudioFile af = AudioFileIO.read(target);
//        AudioHeader ah = af.getAudioHeader();
//        System.out.println( ah.getTrackLength());
//        System.out.println(System.currentTimeMillis());
//        splitFile();
//        System.out.println(System.currentTimeMillis());
    }
    public static void splitFile() throws IOException, NoSuchAlgorithmException {
        List<String> mpm3_name = new ArrayList<>();
        String filename1 = "D:\\testttt\\test.mp3";
        File file = new File(filename1);
        FileInputStream fis = null;
        FileOutputStream fos = null;
        long filesize = file.length();
        long filesizeActual = 0L;
        int splitval = (int) (filesize/10485760) + 1;
        int splitsize = 10485760;
        System.out.println(splitsize +  "splitsize");
        byte[] b = new byte[splitsize];
        System.out.println(filename1 + "            " + filesize + " bytes");
        try {
            fis = new FileInputStream(file);
            String name1 = filename1.replaceAll(".mp3", "");
            String mergeFile = name1 + "_merge.mp3";
            for (int j = 1; j <= splitval; j++) {
                String filecalled = name1 + "_split_" + j + ".mp3";
                fos = new FileOutputStream(filecalled);
                int i = fis.read(b);
                fos.write(b, 0, i);
                fos.close();
                fos = null;
                mpm3_name.add(filecalled);
                System.out.println(filecalled + "    " + i + " bytes");
                filesizeActual += i;
            }
            Assert.assertEquals(filesize, filesizeActual);
            System.out.println(mpm3_name);
//            mergeFileParts(filename1, splitval);
//            check(filename1, mergeFile);
        } finally {
            if(fis != null) {
                fis.close();
            }
            if(fos != null) {
                fos.close();
            }
        }
    }

    private static void mergeFileParts(String filename1, int splitval) throws IOException {
        FileInputStream fis = null;
        FileOutputStream fos = null;
        try {
            String name1 = filename1.replaceAll(".mp3", "");
            String mergeFile = name1 + "_merge.mp3";
            fos = new FileOutputStream(mergeFile);
            for (int j = 1; j <= splitval; j++) {
                String filecalled = name1 + "_split_" + j + ".mp3";
                File partFile = new File(filecalled);
                fis = new FileInputStream(partFile);
                int partFilesize = (int) partFile.length();
                byte[] b = new byte[partFilesize];
                int i = fis.read(b, 0, partFilesize);
                fos.write(b, 0, i);
                fis.close();
                fis = null;
            }
        } finally {
            if(fis != null) {
                fis.close();
            }
            if(fos != null) {
                fos.close();
            }
        }
    }

    private static void check(String expectedPath, String actualPath) throws IOException, NoSuchAlgorithmException {
        System.out.println("check...");

        FileInputStream fis = null;

        try {

            File expectedFile = new File(expectedPath);
            long expectedSize = expectedFile.length();

            File actualFile = new File(actualPath);
            long actualSize = actualFile.length();

            System.out.println("exp=" + expectedSize);
            System.out.println("act=" + actualSize);

            Assert.assertEquals(expectedSize, actualSize);

            fis = new FileInputStream(expectedFile);
            String expected = makeMessageDigest(fis);
            fis.close();
            fis = null;


            fis = new FileInputStream(actualFile);
            String actual = makeMessageDigest(fis);
            fis.close();
            fis = null;

            System.out.println("exp=" + expected);
            System.out.println("act=" + actual);

            Assert.assertEquals(expected, actual);

        } finally {
            if(fis != null) {
                fis.close();
            }
        }
    }

    public static String makeMessageDigest(InputStream is) throws NoSuchAlgorithmException, IOException {
        byte[] data = new byte[1024];
        MessageDigest md = MessageDigest.getInstance("SHA1");
        int bytesRead = 0;

        while(-1 != (bytesRead = is.read(data, 0, 1024))) {
            md.update(data, 0, bytesRead);
        }

        return toHexString(md.digest());
    }

    public static  String toHexString(byte[] digest) {
        StringBuffer sha1HexString = new StringBuffer();
        for(int i = 0; i < digest.length; i++) {
            sha1HexString.append(String.format("%1$02x", Byte.valueOf(digest[i])));
        }

        return sha1HexString.toString();
    }
}
