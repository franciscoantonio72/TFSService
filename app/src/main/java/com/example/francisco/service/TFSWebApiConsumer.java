package com.example.francisco.service;

import android.annotation.TargetApi;
import android.os.Build;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by Francisco on 02/04/2016.
 */
public class TFSWebApiConsumer {
    public enum TipoStatusResposta {
        SUCESSO, ERRO
    }

    private static final String POST = "post";
    private static final String GET = "get";

    public String getTFS(String myurl, String sprint){
        String contentAsString = "";

        myurl += "?sprint="+sprint;

        try {
            contentAsString = Conexao(myurl, GET);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return contentAsString;
    }

    public String postTFSById(String myurl, String id){
        String contentAsString = "";

        myurl += id;

        try {
            contentAsString = Conexao(myurl, POST);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return contentAsString;
    }

    private String Conexao(String myurl, String postGet) throws IOException{
        InputStream is = null;
        int length = 500;
        String contentAsString = "";

        try {
            try {
                URL url = new URL(myurl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                if (postGet.equals(POST)) {
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("ACCEPT-LANGUAGE", "en-US,en;0.5");
                    conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
                    conn.setDoOutput(true);
                } else {
                    conn.setReadTimeout(10000 /* milliseconds */);
                    conn.setConnectTimeout(15000 /* milliseconds */);
                    conn.setRequestMethod("GET");
                    conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
                    conn.setUseCaches(false);
                }
                conn.connect();
                int response = conn.getResponseCode();

                if (response == HttpURLConnection.HTTP_OK) {
                    contentAsString = readStream(conn.getInputStream());
                } else {
                    //
                }
            }catch (Exception ex) {
                ex.printStackTrace();
            }

            return contentAsString;
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    private static String readStream(InputStream in) {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(in));) {

            String nextLine = "";
            while ((nextLine = reader.readLine()) != null) {
                sb.append(nextLine);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }

    private String convertInputStreamToString(InputStream stream, int length) throws IOException, UnsupportedEncodingException {
        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");
        char[] buffer = new char[length];
        reader.read(buffer);
        return new String(buffer);
    }
}
