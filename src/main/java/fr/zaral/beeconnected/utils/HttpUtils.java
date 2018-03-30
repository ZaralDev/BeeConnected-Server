package fr.zaral.beeconnected.utils;


import org.pmw.tinylog.Logger;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * @author Zaral
 */

//Cette classe est utilisée pour les test permettant de simuler une requête venant d'un client
public class HttpUtils {

    public static String sendGet(String url) throws IOException {


        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");

        con.setRequestProperty("User-Agent", "Mozilla/5.0");

        int responseCode = con.getResponseCode();
        Logger.info("Sending 'GET' request to URL : " + url);
        Logger.info("Response Code : " + responseCode);
        System.out.println(responseCode);
        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();


        Logger.info(response.toString());
        return response.toString();
    }

    public static String sendPost(String url, String parameters) throws IOException {
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
        con.setRequestProperty("Content-Type", "application/json");


        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(parameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        Logger.info("Sending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + parameters);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(
                new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuffer response = new StringBuffer();

        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        Logger.info(response.toString());
        return response.toString();

    }

}
