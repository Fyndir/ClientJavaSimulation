package com.company.model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;

public class HTTPTools {

    public static void post(String adress, String data) throws IOException {
        String result = "";
        System.out.println("Données envoyées :");
        System.out.println(data);
        URL url = new URL(adress);
        URLConnection conn = url.openConnection();
        conn.setDoOutput(true);

        OutputStreamWriter writer = new OutputStreamWriter(conn.getOutputStream());
        writer.write(URLEncoder.encode(data,"UTF8"));
        writer.flush();

        BufferedReader reader = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        String ligne;
        while ((ligne = reader.readLine()) != null) {
            result += ligne;
        }

        try {
            writer.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        try {
            reader.close();
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        System.out.println("Message retour :");
        System.out.println(result);
    }
}
