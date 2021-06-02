package sample;

import javafx.application.Application;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;


public class Main extends Application {
    Hand dealer;
    Hand player;
    Deck deck = Deck.getDeck();

    private SimpleBooleanProperty playable = new SimpleBooleanProperty(false);

    Text dealerScore = new Text("Dealer : ");
    Text playerScore = new Text("Player : ");

    HBox playerCards = new HBox(20);
    HBox dealerCards = new HBox(20);

    private void drawCard(Hand hand) {
        hand.takeCard();
    }

    private void startNewGame() {
        playable.set(true);
        deck.newDeck();
        dealer.reset();
        player.reset();

        for (int i = 0; i < Constants.initialNumberOfCards; i++) {
            drawCard(dealer);
            drawCard(player);
        }
    }

    private Parent createContent() {
        dealer = new Hand(dealerCards.getChildren());
        player = new Hand(playerCards.getChildren());

        Pane root = new Pane();
        root.setStyle("-fx-background-image:url('/resources/table.jpg')");

        dealerScore.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 50));
        dealerScore.setFill(Color.BROWN);
        dealerScore.setStrokeWidth(2);
        dealerScore.setStroke(Color.BLACK);
        playerScore.setFont(Font.font("verdana", FontWeight.BOLD, FontPosture.REGULAR, 50));
        playerScore.setFill(Color.BROWN);
        playerScore.setStrokeWidth(2);
        playerScore.setStroke(Color.BLACK);

        Button newGame = new Button();
        newGame.setStyle("-fx-background-color: #ff0000; ");
        newGame.setText("New Game");
        newGame.setOnAction((e) -> {
            startNewGame();
        });

        Button hit = new Button();
        hit.setStyle("-fx-background-color: #ff0000; ");
        hit.setText("Hit");
        hit.setOnAction((e) -> {
            player.takeCard();
        });

        Button stand = new Button();
        stand.setStyle("-fx-background-color: #ff0000; ");
        stand.setText("Stand");
        stand.setOnAction((e) -> {
            while (dealer.score.get() < 17) {
                dealer.takeCard();
            }
            endGame();
        });

        stand.disableProperty().bind(playable.not());
        hit.disableProperty().bind(playable.not());
        newGame.disableProperty().bind(playable);

        playerScore.textProperty().bind(new SimpleStringProperty("Player: ").concat(player.score.asString()));
        dealerScore.textProperty().bind(new SimpleStringProperty("Dealer: ").concat(dealer.score.asString()));

        player.score.addListener((observer, old, newVal) -> {
            if (newVal.intValue() >= 21) {
                endGame();
            }
        });

        dealer.score.addListener((observer, old, newVal) -> {
            if (newVal.intValue() >= 21) {
                endGame();
            }
        });

        Rectangle leftBG = new Rectangle(550, 560);
        leftBG.setArcWidth(50);
        leftBG.setArcHeight(50);
        leftBG.setFill(Color.TRANSPARENT);

        Rectangle rightBG = new Rectangle(230, 560);
        rightBG.setArcWidth(50);
        rightBG.setArcHeight(50);
        rightBG.setFill(Color.TRANSPARENT);

        VBox leftVBox = new VBox(50);
        leftVBox.setAlignment(Pos.TOP_CENTER);
        leftVBox.getChildren().addAll(dealerScore, playerScore, dealerCards, playerCards);

        VBox rightVBox = new VBox(20);
        rightVBox.setAlignment(Pos.CENTER);
        HBox buttonsHBox = new HBox(15, hit, stand, newGame);
        buttonsHBox.setAlignment(Pos.CENTER);
        rightVBox.getChildren().addAll(buttonsHBox);

        HBox rootLayout = new HBox(5);
        rootLayout.setPadding(new Insets(5, 5, 5, 5));
        rootLayout.getChildren().addAll(new StackPane(leftBG, leftVBox), new StackPane(rightBG, rightVBox));

        root.getChildren().add(rootLayout);
        return root;
    }

    private void endGame() {
        playable.set(false);
        Stage primaryStage = new Stage();
        String winner = null;
        Image image = null;
        int dealerScore = dealer.score.get();
        int playerScore = player.score.get();
        if (dealerScore == 21 || playerScore > 21 || dealerScore == playerScore
                || (dealerScore < 21 && dealerScore > playerScore)) {
            image = new Image("file:./src/resources/casino.jpg");
        }
        else {
            image = new Image("file:./src/resources/win.jpg");
        }
        Pane pane = new Pane();
        pane.getChildren().add(new ImageView(image));
        primaryStage.setScene(new Scene(pane));
        primaryStage.show();
    }

    @Override
    public void start(Stage primaryStage) {
        Scene scene = new Scene(createContent());
        primaryStage.setScene(scene);
        primaryStage.setWidth(800);
        primaryStage.setHeight(600);
        primaryStage.setResizable(false);
        primaryStage.setTitle("BlackJack");
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
