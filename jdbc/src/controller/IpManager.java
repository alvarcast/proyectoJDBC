package controller;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class IpManager {

    //Metodo para conseguir la ip publica del usuario
    //Hecho con chatGPT y https://docs.oracle.com/javase/6/docs/api/java/net/URL.html
    public static String getExternalIP() throws Exception {
        HttpURLConnection connection;
        BufferedReader br;

        String ip;
        URL url;

        url = new URL("https://api.ipify.org");

        connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");

        //Respuesta de la API
        br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
        ip = br.readLine();

        connection.disconnect();
        br.close();

        return ip;
    }
}
