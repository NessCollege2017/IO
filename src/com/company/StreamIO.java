package com.company;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Android2017 on 5/12/2017.
 */
public class StreamIO {

    public static void write(String fileName, String data) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            //2) call the write method.
            writer.write(data);
            //writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String read(String fileName) throws IOException {
        //data = content read the file
        //init a file reader.
        InputStream in  = new FileInputStream(fileName);
        return read(in);
    }

    //copy
    public static void copy(String srcFileName, String destFileName) {
        try (InputStream in = new FileInputStream(srcFileName);
             OutputStream out = new FileOutputStream(destFileName)) {

            byte[] buffer = new byte[8192];
            int length = 0;

            while ((length = in.read(buffer, 0, buffer.length)) != -1) {
                out.write(buffer, 0, buffer.length);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    //read from the web
    //https://www.reddit.com/r/Android/.json
    public static String readWebSite(String address) throws IOException {
        URL url = null;
        try {
            url = new URL(address);
        } catch (MalformedURLException e) {
            throw new IOException("failed at parsing the URL.", e);
        }
        URLConnection con = url.openConnection();
        InputStream in = con.getInputStream();
        return read(in);
    }

    public static String read(InputStream in) throws IOException {
        StringBuilder builder = new StringBuilder();//mutable String (appendable String)
        //A) wrap the inputStream in an InputStreamReader
        //B) wrap the reader in a buffered reader
        //C)read the data
        //D)close everything
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {

            String line = null;

            while ((line = reader.readLine()) != null) {
                builder.append(line).append(System.lineSeparator());
            }
        }
        return builder.toString();
    }
}
