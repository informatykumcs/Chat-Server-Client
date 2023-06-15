package com.example.server;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProstySerwer {
    private static List<PrintWriter> clientWriters = new ArrayList<>();

    public static void main(String[] args) {
        try {
            // Tworzenie serwera na porcie 12345
            ServerSocket serverSocket = new ServerSocket(12345);
            System.out.println("Serwer nasłuchuje na porcie 12345...");

            while (true) {
                // Oczekiwanie na połączenie klienta
                Socket clientSocket = serverSocket.accept();
                System.out.println("Połączono z klientem.");

                // Tworzenie strumieni do komunikacji z klientem
                PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
                Scanner in = new Scanner(clientSocket.getInputStream());
                clientWriters.add(out);

                // Odczytywanie loginu od klienta
                String login = in.nextLine();
                System.out.println("Klient " + login + " dołączył.");
                broadcastMessage("Klient " + login + " dołączył.");

                // Odczytywanie i przesyłanie wiadomości
                while (true) {
                    String message = in.nextLine();
                    System.out.println("Klient " + login + " napisał: " + message);
                    broadcastMessage("Klient " + login + " napisał: " + message);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void broadcastMessage(String message) {
        for (PrintWriter writer : clientWriters) {
            writer.println(message);
        }
    }
}