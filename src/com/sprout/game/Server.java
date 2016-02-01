package com.sprout.game;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class Server extends Thread {

    private ServerSocket serverSocket = null;
    private Socket socket = null;
    private ObjectInputStream inStream = null;
    private ObjectOutputStream outStream = null;

    public ConcurrentHashMap<Integer, Socket> games;
    public AtomicInteger id;

    public Server() {
        games = new ConcurrentHashMap<>();
        id = new AtomicInteger(0);
    }

    @Override
    public void run() {
        // create server socket
        try {
            serverSocket = new ServerSocket(4445);

//            inStream = new ObjectInputStream(socket.getInputStream());
//            outStream = new ObjectOutputStream(socket.getOutputStream());
        } catch (IOException ex) {
            System.err.println("PO NOVOI");
            return;
        }

        while (true) {
            try {
                socket = serverSocket.accept();
                System.out.println("Client has connected");
            } catch (IOException e) {
                e.printStackTrace();
            }

            System.out.println("Request handler thread started");
            new RequestHandlerThread(this, socket).start();
        }
    }
}
