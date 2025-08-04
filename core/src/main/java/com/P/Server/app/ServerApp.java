package com.P.Server.app;

import java.util.ArrayList;

public class ServerApp {
    public static final int TIMEOUT_MILLIS = 500;
    private static final ArrayList<ClientConnectionThread> connections = new ArrayList<>();
    private static boolean exitFlag = false;
    private static ListenerThread listenerThread;

    public static boolean isEnded() {
        return exitFlag;
    }

    public static void setListenerThread(ListenerThread listenerThread) {
        ServerApp.listenerThread = listenerThread;
    }

    public static void startListening() {
        if (listenerThread != null && !listenerThread.isAlive()) {
            listenerThread.start();
        } else {
            throw new IllegalStateException("Listener thread is already running or not set.");
        }
    }

    public static void endAll() {
        exitFlag = true;
        for (ClientConnectionThread connection : connections)
            connection.end();
        connections.clear();
        listenerThread.end();
    }

    public static void removeClientConnection(ClientConnectionThread connection) {
        if (connection != null) {
            connections.remove(connection);
            connection.end();
        }
    }

    public static void addClientConnection(ClientConnectionThread connection) {
        if (connection != null && !connections.contains(connection))
            connections.add(connection);
    }

}
