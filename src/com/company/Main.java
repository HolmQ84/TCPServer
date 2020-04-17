package com.company;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        boolean stillrunning = true;
        try{
            ServerSocket serverSocket = new ServerSocket(6780); // Man skal angive et portnummer hvor trafikken skal køre igennem.
            System.out.println("Er klar til at modtage klient");
            Socket socket = serverSocket.accept(); // Står og venter på en klient. Det hedder at 'blokere'.
            System.out.println("Klient forbindelse oprettet - "+socket);
            InputStreamReader isr = new InputStreamReader(socket.getInputStream());
            BufferedReader bufferedReader = new BufferedReader(isr);
            PrintWriter printWriter = new PrintWriter(socket.getOutputStream(), true);
            Scanner scan = new Scanner(System.in);
            while (stillrunning) {
                // Modtager en besked fra klienten.
                String currentMsg = bufferedReader.readLine();
                System.out.println("Modtaget besked: "+currentMsg);
                // Sender besked til klienten.

                System.out.print("Skriv din besked -> ");
                printWriter.println(scan.nextLine());
                if (currentMsg.equalsIgnoreCase("quit")) {
                    stillrunning = false;
                }
            }
            socket.close(); // For at afslutte denne socket.
        } catch(Exception e){
            System.out.println("Error: "+ e.getMessage());
        }
    }
}
