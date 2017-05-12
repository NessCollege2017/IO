package com.company;

import java.io.*;

public class Main {

    public static void main(String[] args) {
        try {
            System.out.println(StreamIO.readWebSite("http://www.omdbapi.com/"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
