package client;

import game.Card;
import game.Player;

import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Scanner;

public class Client {
    private static final String SERVER_ADDRESS = "localhost";
    private static final int SERVER_PORT = 12345;
    private ObjectOutputStream out;
    private ObjectInputStream in;

    public void startClient() {
        try (Socket socket = new Socket(SERVER_ADDRESS, SERVER_PORT);
             Scanner scanner = new Scanner(System.in)) {
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());

            System.out.print("Enter your name: ");
            String name = scanner.nextLine();

            out.writeObject(name);

            while (true) {
                Object response = in.readObject();
                if (response instanceof String) {
                    System.out.println((String) response);
                } else if (response instanceof Card) {
                    System.out.println("Top card: " + response.toString());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Client().startClient();
    }
}
