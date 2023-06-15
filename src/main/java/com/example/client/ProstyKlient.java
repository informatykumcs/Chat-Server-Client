package com.example.client;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ProstyKlient {
    public static void main(String[] args) {
        try {
            // Tworzenie połączenia z serwerem na porcie 12345
            Socket socket = new Socket("localhost", 12345);
            System.out.println("Połączono z serwerem.");

            // Tworzenie strumieni do komunikacji z serwerem
            PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
            Scanner in = new Scanner(socket.getInputStream());
            Scanner scanner = new Scanner(System.in);

            // Podawanie loginu
            System.out.print("Podaj swój login: ");
            String login = scanner.nextLine();
            out.println(login);

            // Odbieranie wiadomości od serwera w osobnym wątku
            Thread receiveThread = new Thread(() -> {
                while (true) {
                    String response = in.nextLine();
                    System.out.println(response);
                }
            });
            receiveThread.start();

            // Pisanie i wysyłanie wiadomości do serwera
            while (true) {
                String message = scanner.nextLine();
                out.println(message);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}