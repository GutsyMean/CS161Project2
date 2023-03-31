package blackJackGame;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class BlackJackTableGUI extends Application {

    private Deck deck;
    private Player dealer;
    private Player player;
    private Label playerHandValueLabel;
    private Label dealerHandValueLabel;
    private Label gameResultLabel;
    private Button standButton;
    private Button hitButton;
    private Button startButton;
    private HBox playerHandValueBox;
    private HBox dealerHandValueBox;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // Create the deck and players
        deck = new Deck();
        dealer = new Player();
        player = new Player();

        // Create the GUI elements
        Label titleLabel = new Label("Blackjack");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        titleLabel.setTextFill(Color.WHITE);
        HBox titleBox = new HBox(titleLabel);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setPadding(new Insets(10));
        
        playerHandValueLabel = new Label("Hand Value: 0");
        playerHandValueLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        playerHandValueLabel.setTextFill(Color.WHITE);
        HBox playerHandValueBox = new HBox(playerHandValueLabel);
        playerHandValueBox.setAlignment(Pos.CENTER_LEFT);
        playerHandValueBox.setPadding(new Insets(0, 0, 0, 100));
        
        dealerHandValueLabel = new Label("Hand Value: ?");
        dealerHandValueLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        dealerHandValueLabel.setTextFill(Color.WHITE);
        HBox dealerHandValueBox = new HBox(dealerHandValueLabel);
        dealerHandValueBox.setAlignment(Pos.CENTER_LEFT);
        dealerHandValueBox.setPadding(new Insets(0, 0, 0, 100));
        
        HBox playerCardBox = new HBox();
        playerCardBox.setAlignment(Pos.CENTER);
        playerCardBox.setPadding(new Insets(10));
        
        HBox dealerCardBox = new HBox();
        dealerCardBox.setAlignment(Pos.CENTER);
        dealerCardBox.setPadding(new Insets(10));
        
        gameResultLabel = new Label("");
        gameResultLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        gameResultLabel.setTextFill(Color.WHITE);
        HBox gameResultBox = new HBox(gameResultLabel);
        gameResultBox.setAlignment(Pos.CENTER);
        gameResultBox.setPadding(new Insets(0, 0, 0, 280));

        Button startButton = new Button("Start");
        startButton.setOnAction(e -> startGame());
        
        Button hitButton = new Button("Hit");
        hitButton.setDisable(true);
        hitButton.setOnAction(e -> 
        {
            player.hit();
            updateHand(player, playerCardBox, playerHandValueLabel);
            if (player.bust())
            {
                endGame();
            }
        });
        
        Button standButton = new Button("Stand");
        standButton.setDisable(true);
        standButton.setOnAction(event -> 
        {
    	    while (dealer.valueOfHand() < 17) 
    	    {
    	    	dealer.hit();
    	        updateHand(dealer, dealerHandValueBox, dealerHandValueLabel);
    	    }
    	    endGame();
    	});
        
        GridPane grid = new GridPane();
    }
    

	public void startGame() 
	{
	    deck.reset();
	    dealer.clearHand();
	    player.clearHand();
	    dealer.hit();
	    player.hit();
	    updateHand(dealer, dealerHandValueBox, dealerHandValueLabel);
	    updateHand(player, playerHandValueBox, playerHandValueLabel);
	    gameResultLabel.setText("");
	    startButton.setDisable(true);
	    hitButton.setDisable(false);
	    standButton.setDisable(false);
	}
	public void updateHand(Player p, HBox box, Label handValue) 
	{
	    box.getChildren().clear();
	    for (Card card : p.getHand()) 
	    {
	        ImageView iv = new ImageView(card.getFace());
//	        iv.setFitWidth(100);
//	        iv.setPreserveRatio(true);
	        box.getChildren().add(iv);
	    }
	    handValue.setText(String.valueOf(p.valueOfHand()));
	}
	private void endGame() {
	    hitButton.setDisable(true);
	    standButton.setDisable(true);
	    int playerValue = player.valueOfHand();
	    int dealerValue = dealer.valueOfHand();
	    if (playerValue > 21) {
	        gameResultLabel.setText("Player Bust! Dealer Wins!");
//	        dealer.incrementScore();
	    } else if (dealerValue > 21) {
	        gameResultLabel.setText("Dealer Bust! Player Wins!");
//	        player.incrementScore();
	    } else if (playerValue > dealerValue) {
	        gameResultLabel.setText("Player Wins!");
//	        player.incrementScore();
	    } else if (dealerValue > playerValue) {
	        gameResultLabel.setText("Dealer Wins!");
//	        dealer.incrementScore();
	    } else {
	        gameResultLabel.setText("Tie Game!");
	    }
	    startButton.setDisable(false);
	}
	public static void main(String[] args) 
	{
		launch(args);
	}
}
