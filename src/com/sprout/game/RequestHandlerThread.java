package com.sprout.game;

import sun.net.ConnectionResetException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.SocketException;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created by megasoch on 01.02.2016.
 */
public class RequestHandlerThread extends Thread {
    Server server;
    Socket client;

    public RequestHandlerThread(Server server, Socket client) {
        this.server = server;
        this.client = client;
    }

    @Override
    public void run() {
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(client.getInputStream()));
            String request = reader.readLine();
            if ("create".equals(request)) {
                server.games.put(server.id.getAndIncrement(), client);
                System.out.println("Game created, exiting thread");
                return;
            } else if ("join".equals(request)) {
                int gameId = Integer.parseInt(reader.readLine());
                System.out.println("Client joined game : " + gameId);
                Socket companion = server.games.get(gameId);
                server.games.remove(gameId);
                System.out.println("Communication thread started with game id: " + gameId);
                new ClientCommunicationThread(companion, client).start();
                return;

            } else if ("games".equals(request)) {
                OutputStream out = client.getOutputStream();
                for (Integer i : server.games.keySet()) {
                    out.write((i.toString() + "\n").getBytes());
                }
                out.write(("endofgames\n").getBytes());

                run();
                return;

            } else {
                System.err.println("Bad request: " + request);
                System.err.println("Stopping thread");
                return;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
