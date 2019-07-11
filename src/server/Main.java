package server;

import logic.Message;
import server.SocketPair;

import java.io.IOException;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.Scanner;

public class Main {
    private static ArrayList<SocketPair> sockets = new ArrayList<>();

    public static void main(String[] args) {
        try {
            ServerSocket serverSocket = new ServerSocket(8000);

            new Thread() {
                Scanner scanner = new Scanner(System.in);

                @Override
                public void run() {
                    if (scanner.nextLine().equals("power off")) {
                        System.exit(0);
                    }
                }
            }.start();

            while (true) {
                Socket socket = serverSocket.accept();

                new Thread(() -> {
                    System.out.println("socket added");
                    try {
                        sockets.add(new SocketPair(socket));
                        Scanner scanner = new Scanner(socket.getInputStream());

                        while (socket.isConnected()) {
                            if (scanner.hasNextLine()) {
                                String message = scanner.nextLine();
                                for (SocketPair other : sockets) {
                                    synchronized (other) {
                                        other.getFormatter().format(message + "\n");
                                        other.getFormatter().flush();
                                    }
                                }
                                System.out.println(message);
                            }
                        }
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
