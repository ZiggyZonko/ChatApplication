package server;

import java.io.*;
import java.net.*;
import java.util.*;

public class ChatServer {
    // List to keep track of all connected clients
    private static final List<ClientHandler> clients = new ArrayList<>();

    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5000);
        System.out.println("Server started. Waiting for clients...");

        while (true) {
            Socket clientSocket = serverSocket.accept();
            System.out.println("Client connected: " + clientSocket);

            // Spawn a new thread for each ChatApplication.ChatApplication.client
            ClientHandler clientThread = new ClientHandler(clientSocket, clients);
            clients.add(clientThread);
            new Thread(clientThread).start();
        }
    }
}

class ClientHandler implements Runnable {
    private Socket clientSocket;
    private List<ClientHandler> clients;
    private PrintWriter out;
    private BufferedReader in;
    private String randomString; // Unique random string for each ChatApplication.ChatApplication.client

    public ClientHandler(Socket socket, List<ClientHandler> clients) throws IOException {
        this.clientSocket = socket;
        this.clients = clients;
        this.out = new PrintWriter(clientSocket.getOutputStream(), true);
        this.in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        // Generate a new random string for this ChatApplication.ChatApplication.client
        this.randomString = generateRandomString();
    }

    public void run() {
        try {
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                // Prepend the random string to the received message
                String formattedMessage = randomString + ": " + inputLine;

                // Broadcast the modified message to all clients
                for (ClientHandler aClient : clients) {
                    aClient.out.println(formattedMessage);
                }
            }
        } catch (IOException e) {
            System.out.println("An error occurred: " + e.getMessage());
        } finally {
            try {
                in.close();
                out.close();
                clientSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private String generateRandomString() {
        int randomNumber = new Random().nextInt(9000) + 1000; // Generate a 4-digit number
        return String.valueOf(randomNumber);
    }
}
