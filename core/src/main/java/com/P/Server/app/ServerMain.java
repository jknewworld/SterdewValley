package com.P.Server.app;


public class ServerMain {
    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("Usage: java tracker.TrackerMain <port>");
            return;
        }

        try {
            int port = Integer.parseInt(args[0]);
            ServerApp.setListenerThread(new ListenerThread(port));
            ServerApp.startListening();
        } catch (Exception e) {
            System.err.println("Error starting tracker: " + e.getMessage());
        }
    }
}
