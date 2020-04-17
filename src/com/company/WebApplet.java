package com.company;

import org.json.simple.JSONObject;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class WebApplet {
    static int count = 0;

    public static void main(String[] args) {
        try{
            ServerSocket serverSocket = new ServerSocket(6782);
            // Man skal angive et portnummer hvor trafikken skal køre igennem.

            System.out.println("Er klar til at modtage klient");
            Socket socket = serverSocket.accept(); // Står og venter på en klient.
            // Det hedder at 'blokere'.
            System.out.println("Klient forbindelse oprettet!");
            // Server skal kunne modtage tekst.
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            // Sender besked til klienten.
            InputStreamReader isr = new InputStreamReader(socket.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(isr);
            System.out.println("Modtager fra klient..");
            System.out.println(bufferedReader.readLine());

            // 1. Lav noget tekst.
            String message = "hello";
            String name = "John Doe";

            // 2. Lav om til JSON format.
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("message", message);
            jsonObject.put("name", name);

            // 3. Lav om til en string.
            printWriter.println(jsonObject.toJSONString());

            socket.close(); // For at afslutte denne socket.

        } catch(Exception e){
            System.out.println("Error: "+ e.getMessage());
        }
    }
}
