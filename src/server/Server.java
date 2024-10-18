package server;

import game.Game;
import game.Player;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

public class Server {
    private static final int PORT = 12345;
    private List<Player> players = new ArrayList<>();
    private Game game;

    public void startServer() {
        try (ServerSocket serverSocket = new ServerSocket(PORT)) {
            System.out.println("Server is running...");

            while (true) {
                Socket clientSocket = serverSocket.accept();
                ObjectOutputStream out = new ObjectOutputStream(clientSocket.getOutputStream());
                ObjectInputStream in = new ObjectInputStream(clientSocket.getInputStream());

                String playerName = (String) in.readObject();
                Player player = new Player(playerName);
                players.add(player);

                if (players.size() >= 2) {
                    game = new Game(players);
                    game.startGame();
                    broadcastGameState();
                }

                // Spawn a new thread for each connected player
                new Thread(new ServerClientController(clientSocket, player, in, out, game)).start();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void broadcastGameState() {
        // Send game state to all clients
    }

    public static void main(String[] args) {
        new Server().startServer();
    }
}
