package com.example.waqar.webservices;

import android.util.Base64;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpManager {

    public static String getData(String uri) {

        BufferedReader bf = null;

        try {
            URL url = new URL(uri);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            StringBuilder sb = new StringBuilder();
            bf = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;

            while ((line = bf.readLine()) != null) {
                sb.append(line + "\n");
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (bf != null) {
                try {
                    bf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
    }

    public static String getData(String uri,String username,String password) {

        byte[] loginbytes=(username + ":" + password).getBytes();
        StringBuilder builder=new StringBuilder()
                .append("Basic ")
                .append(Base64.encodeToString(loginbytes,Base64.DEFAULT));


        BufferedReader bf = null;

        try {
            URL url = new URL(uri);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.addRequestProperty("Authorization",builder.toString());
            StringBuilder sb = new StringBuilder();
            bf = new BufferedReader(new InputStreamReader(conn.getInputStream()));
            String line;

            while ((line = bf.readLine()) != null) {
                sb.append(line + "\n");
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            if (bf != null) {
                try {
                    bf.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    return null;
                }
            }
        }
    }

}