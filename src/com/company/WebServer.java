package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class WebServer {
    static int count = 0;

    public static void main(String[] args) {
        try{
            ServerSocket serverSocket = new ServerSocket(6781);
            // Man skal angive et portnummer hvor trafikken skal køre igennem.
            while (count < 3) {
                System.out.println("Er klar til at modtage klient");
                Socket socket = serverSocket.accept(); // Står og venter på en klient.
                // Det hedder at 'blokere'.
                System.out.println("Klient forbindelse oprettet!");
                // Server skal kunne modtage tekst.
                PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
                // Sender besked til klienten.
                printWriter.println("HTTP/1.0 200 OK");
                printWriter.println("Content-Type: text/html; charset=utf-8");
                printWriter.println("");
                printWriter.println("How are you doing?");
                count++;
                socket.close(); // For at afslutte denne socket.
            }

        } catch(Exception e){
            System.out.println("Error: "+ e.getMessage());
        }
    }
}
