package com.sprout.game;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by megasoch on 01.02.2016.
 */
public class ClientCommunicationThread extends Thread {
    Socket first;
    Socket second;

    ClientCommunicationThread(Socket first, Socket second) {
        this.first = first;
        this.second = second;
    }

    @Override
    public void run() {

            try {
                ObjectOutputStream firstOut = new ObjectOutputStream(first.getOutputStream());
                ObjectInputStream firstIn = new ObjectInputStream(first.getInputStream());

                ObjectOutputStream secondOut = new ObjectOutputStream(second.getOutputStream());
                ObjectInputStream secondIn = new ObjectInputStream(second.getInputStream());


                while(true) {
                    // read from 1
                    GameLogic current = (GameLogic) firstIn.readObject();
                    // write to 2
                    secondOut.writeObject(current);
                    secondOut.flush();

                    // read from 2
                    current = (GameLogic) secondIn.readObject();
                    // write to 1
                    firstOut.writeObject(current);
                    firstOut.flush();
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

    }
}
