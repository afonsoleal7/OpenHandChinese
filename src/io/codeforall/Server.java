package io.codeforall;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Level;

public class Server {
    private Socket clientSocket;
    private ServerSocket serverSocket;
    private BufferedReader bufferedReader;
    public List<ServerWorker> workers;

    public int ConnectionCount = 0;

    private String name;


    public Server(int port) throws IOException {
        // bind the socket to specified port
        System.out.println("Binding to port " + port);
        serverSocket = new ServerSocket(port);
        start(serverSocket);
    }

    public static void main(String args[]) {
        //if (args.length == 0) {
        //System.out.println("Usage: java ChatServer [port]");
        //System.exit(1);
        //}

        //try {
        // try to create an instance of the ChatServer at port specified at args[0]
        try {
            new Server(8200);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //} catch (NumberFormatException ex) {
        // write an error message if an invalid port was specified by the user
        //System.out.println("Invalid port number " + args[0]);
        //} catch (IOException e) {
        //throw new RuntimeException(e);
    }


    private void start(ServerSocket bindSocket) {
        workers = Collections.synchronizedList(new LinkedList<>());
        ExecutorService CachedPool = Executors.newCachedThreadPool();
        while (true) {


            try {

                // accepts client connections and instantiates worker dispatchers

                ServerWorker clientWorker = new ServerWorker(bindSocket.accept());

                workers.add(clientWorker);
                ConnectionCount++;

                CachedPool.submit(clientWorker);

                //InicialCards();
                System.out.println("b");


                // launch the client thread
                //Thread clientThread = new Thread(clientDispatcher);
                //clientThread.start();


            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }


    }


    public void InicialCards() throws IOException{
        synchronized (workers) {
            for (ServerWorker s : workers) {
                s.setInicialCards();
            }
        }
    }


    private class ServerWorker extends Player implements Runnable {


        public ServerWorker(Socket clientSocket) throws IOException {
            super(clientSocket);


        }


        @Override
        public void run() {
            while (true) {

                System.out.println("abba");
                try {
                    super.playRound2();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }

                //super.enterUsername();


                /*try {
                    super.setInicialCards();
                } catch (IOException e) {
                    throw new RuntimeException(e);

                }
                try {
                    while (super.areThereCardsLeft()) {
                        super.playRound();
                    }
                    out.write("no more cards to add");

                } catch (IOException e) {
                    throw new RuntimeException(e);
                }


                // try {

                // read line from socket input reader
                    /*String line = in.readLine();

                    // if received /quit close break out of the reading loop
                    if (line.equals("/quit")) {
                        System.out.println("Client closed, exiting");
                        workers.remove(this);
                        in.close();


                    }else {
                        //show message to all
                        sendMessagetoAll(name + " : " + line);
                    }



                    // show the received line to the console
                    System.out.println(line);

                } catch (IOException ex) {

                    System.out.println("Receiving error: " + ex.getMessage());

                }

            }*/


            }

        }
    }
}

