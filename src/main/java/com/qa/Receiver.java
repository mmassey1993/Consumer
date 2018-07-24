package com.qa;

import org.springframework.stereotype.Component;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

@Component
public class Receiver {

    private final String USER_AGENT = "Mozilla/5.0";

    public void receiveMessage(String message) throws IOException {
        //System.out.println("Received <" + message + ">");
        String url = "http://localhost:9000/employeeservice/employees/bob";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("GET");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Content-Type", "application/json");
        int responseCode = con.getResponseCode();
        System.out.println("GET Response Code: " + responseCode);

        if (responseCode == HttpURLConnection.HTTP_OK) { // success
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    con.getInputStream()));
            String inputLine;
            StringBuffer response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) {
                response.append(inputLine);
            }
            in.close();

            // print result
            System.out.println("The response is ");
            System.out.println(response.toString());
        } else {
            System.out.println("GET request not worked");
        }
    }

    public void postRequest(String message) throws IOException {
        String url = "http://localhost:9000/employeeservice/employees/bob";
        URL obj = new URL(url);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        con.setRequestMethod("POST");
        con.setRequestProperty("User-Agent", USER_AGENT);
        con.setRequestProperty("Content-Type", "application/json");

        String postJsonData = "Hello World John Boy!";

        con.setDoOutput(true);
        DataOutputStream os = new DataOutputStream(con.getOutputStream());
        os.writeBytes(postJsonData);
        os.flush();
        os.close();

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post Data : " + postJsonData);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String output;
        StringBuffer response = new StringBuffer();

        while ((output = in.readLine()) != null){
            response.append(output);
        }
        in.close();

        System.out.println(response.toString());
    }

}
