package server;

import game.Game;
import game.Player;
import java.net.Socket;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

public class ServerClientController implements Runnable {
    private ObjectOutputStream out;
    private Game game;

    public ServerClientController(Socket clientSocket, Player player, ObjectInputStream in, ObjectOutputStream out, Game game) {
        this.clientSocket = clientSocket;
        this.player = player;
        this.in = in;
        this.out = out;
        this.game = game;
    }

    @Override
    public void run() {
        try {
            while (true) {
                // Handle client actions
                out.writeObject("Current player: " + game.getCurrentPlayer().getName());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
