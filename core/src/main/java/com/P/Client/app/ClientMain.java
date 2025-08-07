package com.P.Client.app;

import java.util.Scanner;

public class ClientMain {
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        if (args.length < 2) {
            System.err.println("Usage: java peer.PeerMain <self-address:port> <tracker-address:ip>");
            return;
        }

        try {
            ClientApp.initFromArgs(args);
            ClientApp.connectServer();
            System.out.println("connected to server!");
        } catch (Exception e) {
            System.err.println("Error initializing peer: " + e.getMessage());
            return;
        }
        while (!ClientApp.isEnded()) {
            String result = ClientCLIController.processCommand(scanner.nextLine().trim());
            System.out.println(result);
        }
        scanner.close();
    }
}
