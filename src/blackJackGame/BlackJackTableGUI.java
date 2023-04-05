package blackJackGame;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class BlackJackTableGUI extends Application 
{

    private Deck deck;
    private Player dealer;
    private Player player;
    private Label playerHandValueLabel;
    private Label dealerHandValueLabel;
    private Label gameResultLabel;
    private Label titleLabel;
    private Button standButton;
    private Button hitButton;
    private Button startButton;
    private HBox playerHandValueBox;
    private HBox dealerHandValueBox;
    private HBox gameResultBox;
    private HBox dealerCardBox;
    private HBox playerCardBox;
    private HBox titleBox;
    private HBox dealerValue;

    @Override
    public void start(Stage primaryStage) throws Exception 
    {
        // Create the deck and players
        deck = new Deck();
        dealer = new Player();
        player = new Player();

        // Create the GUI elements
        titleLabel = new Label("Blackjack");
        titleLabel.setFont(Font.font("Arial", FontWeight.BOLD, 24));
        titleLabel.setTextFill(Color.BLACK);
        titleBox = new HBox(titleLabel);
        titleBox.setAlignment(Pos.CENTER);
        titleBox.setPadding(new Insets(10));
        
        playerHandValueLabel = new Label("Player Hand Value: ");
        playerHandValueLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        playerHandValueLabel.setTextFill(Color.BLACK);
        playerHandValueBox = new HBox(playerHandValueLabel);
        playerHandValueBox.setAlignment(Pos.CENTER_LEFT);
        playerHandValueBox.setPadding(new Insets(0, 0, 0, 100));
        
        dealerHandValueLabel = new Label("Dealer Hand Value: ");
        dealerHandValueLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        dealerHandValueLabel.setTextFill(Color.BLACK);
        dealerHandValueBox = new HBox();
        dealerValue = new HBox(dealer.valueOfHand());
        dealerHandValueBox.getChildren().addAll(dealerHandValueLabel, dealerValue);
        dealerHandValueBox.setAlignment(Pos.CENTER_LEFT);
        dealerHandValueBox.setPadding(new Insets(0, 0, 0, 100));
        
        playerCardBox = new HBox();
        playerCardBox.setAlignment(Pos.CENTER);
        playerCardBox.setPadding(new Insets(10));

        dealerCardBox = new HBox();
        dealerCardBox.setAlignment(Pos.CENTER);
        dealerCardBox.setPadding(new Insets(10));
        
        gameResultLabel = new Label("");
        gameResultLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        gameResultLabel.setTextFill(Color.BLACK);
        gameResultBox = new HBox(gameResultLabel);
        gameResultBox.setAlignment(Pos.CENTER);
        gameResultBox.setPadding(new Insets(0, 0, 0, 280));

        startButton = new Button("Start");
        startButton.setOnAction(e -> startGame());
        
        hitButton = new Button("Hit");
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
        
        standButton = new Button("Stand");
        standButton.setDisable(true);
        standButton.setOnAction(event -> 
        {
    	    while (dealer.valueOfHand() < 17) 
    	    {
    	    	dealer.hit();
    	        updateHand(dealer, dealerCardBox, dealerHandValueLabel);
    	    }
    	    endGame();
    	});
        
        GridPane grid = new GridPane();
        grid.add(titleBox, 0, 0, 3, 1);
        grid.add(dealerHandValueBox, 0, 1);
        grid.add(dealerCardBox, 1, 1);
        grid.add(playerHandValueBox, 0, 2);
        grid.add(playerCardBox, 1, 2);
        grid.add(gameResultBox, 0, 3, 3, 1);
        grid.add(startButton, 0, 4);
        grid.add(hitButton, 1, 4);
        grid.add(standButton, 2, 4);
        grid.setStyle("-fx-background-color: forestgreen");

        
        Scene scene = new Scene(grid);

        
        primaryStage.setTitle("Blackjack");
        primaryStage.setScene(scene);
        primaryStage.setHeight(750);
        primaryStage.setWidth(800);
        primaryStage.show();
    }
    

	public void startGame() 
	{
	    deck.reset();
	    dealer.clearHand();
	    player.clearHand();
	    dealer.hit();
	    player.hit();
	    updateHand(dealer, dealerCardBox, dealerHandValueLabel);
	    updateHand(player, playerCardBox, playerHandValueLabel);
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
	    	Image image = new Image("file:C:\\Users\\timot\\eclipse-workspace\\Project2\\cards\\"+card.getFace()+".png");
	        ImageView iv = new ImageView(image);
	        box.getChildren().add(iv);
	    }
	    handValue.setText(String.valueOf(p.valueOfHand()));
	}
	private void endGame() 
	{
	    hitButton.setDisable(true);
	    standButton.setDisable(true);
	    int playerValue = player.valueOfHand();
	    int dealerValue = dealer.valueOfHand();
	    if (playerValue > 21) {
	        gameResultLabel.setText("Player Bust! Dealer Wins!");
	    } else if (dealerValue > 21) {
	        gameResultLabel.setText("Dealer Bust! Player Wins!");
	    } else if (playerValue > dealerValue) {
	        gameResultLabel.setText("Player Wins!");
	    } else if (dealerValue > playerValue) {
	        gameResultLabel.setText("Dealer Wins!");
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
