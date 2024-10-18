package client;

import game.Card;
import game.RegularCard;
import game.WildCard;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import java.util.ArrayList;

public class ClientGUI extends Application {

    private ObjectOutputStream out;
    private ObjectInputStream in;
    private List<Card> hand = new ArrayList<>();
    private Card topCard;

    private VBox handBox = new VBox();
    private Text topCardText = new Text("Top Card: ");
    private TextArea messageArea = new TextArea();

    private Button unoButton = new Button("UNO");
    private Button drawButton = new Button("Draw");
    private Button playButton = new Button("Play Card");

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("UNO Client");

        // Setup the layout
        BorderPane layout = new BorderPane();

        // Top area: Display the top card and player status
        VBox topArea = new VBox(10, new Text("UNO Game"), topCardText);
        topArea.setPadding(new Insets(10));
        layout.setTop(topArea);

        // Center: Display player's hand
        handBox.setPadding(new Insets(10));
        handBox.setSpacing(10);
        layout.setCenter(handBox);

        // Bottom: Draw, Play, and UNO buttons
        HBox buttonBox = new HBox(10, drawButton, playButton, unoButton);
        buttonBox.setPadding(new Insets(10));
        layout.setBottom(buttonBox);

        // Right: Message area for game updates
        messageArea.setEditable(false);
        messageArea.setWrapText(true);
        messageArea.setPrefHeight(200);
        layout.setRight(messageArea);

        // Set actions for buttons
        drawButton.setOnAction(e -> drawCard());
        playButton.setOnAction(e -> playSelectedCard());
        unoButton.setOnAction(e -> sayUno());

        // Initialize the GUI
        Scene scene = new Scene(layout, 600, 400);
        primaryStage.setScene(scene);
        primaryStage.show();

        // Connect to server and start receiving game state updates
        connectToServer();
        new Thread(this::receiveGameUpdates).start();
    }

    private void connectToServer() {
        try {
            Socket socket = new Socket("localhost", 12345);
            out = new ObjectOutputStream(socket.getOutputStream());
            in = new ObjectInputStream(socket.getInputStream());

            String playerName = askPlayerName();
            out.writeObject(playerName);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String askPlayerName() {
        TextInputDialog dialog = new TextInputDialog("Player");
        dialog.setTitle("Enter Name");
        dialog.setHeaderText("Enter your name to join the game");
        dialog.setContentText("Name:");
        return dialog.showAndWait().orElse("Player");
    }

    private void receiveGameUpdates() {
        try {
            while (true) {
                Object response = in.readObject();
                if (response instanceof String) {
                    appendMessage((String) response);
                } else if (response instanceof Card) {
                    updateTopCard((Card) response);
                } else if (response instanceof List) {
                    updateHand((List<Card>) response);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void updateTopCard(Card card) {
        topCard = card;
        topCardText.setText("Top Card: " + card.toString());
    }

    private void updateHand(List<Card> newHand) {
        hand = newHand;
        handBox.getChildren().clear();
        for (Card card : hand) {
            Button cardButton = new Button(card.toString());
            cardButton.setOnAction(e -> selectCard(card));
            handBox.getChildren().add(cardButton);
        }
    }

    private void appendMessage(String message) {
        messageArea.appendText(message + "\n");
    }

    private void selectCard(Card card) {
        playButton.setUserData(card);
    }

    private void drawCard() {
        try {
            out.writeObject("DRAW");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void playSelectedCard() {
        try {
            Card selectedCard = (Card) playButton.getUserData();
            if (selectedCard != null) {
                out.writeObject(selectedCard);
            } else {
                appendMessage("Please select a card to play!");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void sayUno() {
        try {
            out.writeObject("UNO");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
