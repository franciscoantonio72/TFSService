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

    public String getTFS(String myurl, String sprint) throws IOException {
        InputStream is = null;
        int length = 500;
        String contentAsString = "";

        myurl += "?sprint=149";

        try {
            URL url = new URL(myurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 /* milliseconds */);
            conn.setConnectTimeout(15000 /* milliseconds */);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("Content-Type", "application/json;charset=utf-8");
            conn.setUseCaches(false);
            //conn.setDoInput(true);
            conn.connect();
            int response = conn.getResponseCode();

            if (response == HttpURLConnection.HTTP_OK) {
                contentAsString = readStream(conn.getInputStream());
            } else {
                //
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