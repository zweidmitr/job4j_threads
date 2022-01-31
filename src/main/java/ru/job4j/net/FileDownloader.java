package ru.job4j.net;

import java.io.BufferedInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;

public class FileDownloader {
    public static void main(String[] args) throws Exception {
        String file = "https://raw.githubusercontent.com/peterarsentev/course_test/master/pom.xml";
        try (BufferedInputStream in = new BufferedInputStream(new URL(file).openStream());
             FileOutputStream fileOutputstream = new FileOutputStream("pom_tmp.xml")) {
            byte[]  dataBuffer = new byte[1024];
            int byteRead;
            while ((byteRead = in.read(dataBuffer,0,1024)) != -1) {
                fileOutputstream.write(dataBuffer, 0, byteRead);
                Thread.sleep(1000);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
