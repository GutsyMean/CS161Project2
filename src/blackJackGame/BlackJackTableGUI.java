package blackJackGame;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
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
    private Label playerScoreLabel;
    private Label dealerHandValueLabel;
    private Label dealerScoreLabel;
    private Label gameResultLabel;
    private Label dealerHandLabel;
    private Label playerHandLabel;
    private Button standButton;
    private Button hitButton;
    private Button startButton;
    private HBox playerHandValueBox;
    private HBox dealerHandValueBox;
    private HBox gameResultBox;
    private HBox dealerCardBox;
    private HBox playerCardBox;
    private HBox playerHandBox;
    private HBox dealerHandBox;
    private HBox buttonBox;
    private int playerScore = 0;
    private int dealerScore = 0;
    private Label playerScoreBox;
    private Label dealerScoreBox;
    private VBox values;

    @Override
    public void start(Stage primaryStage) throws Exception 
    {
        // Create the deck and players
        deck = new Deck();
        dealer = new Player();
        player = new Player();

        // Create the GUI elements
        
        playerHandLabel = new Label("Player Hand"); 
        playerHandLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        playerHandLabel.setTextFill(Color.WHITE);
        playerHandValueLabel = new Label("Player Value: ");
        playerHandValueLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        playerHandValueLabel.setTextFill(Color.WHITE);
        playerHandValueBox = new HBox(200, playerHandLabel, playerHandValueLabel);
        playerHandValueBox.setAlignment(Pos.CENTER_LEFT);
        playerHandValueBox.setPadding(new Insets(0, 0, 0, 200));
        
        dealerHandLabel = new Label("Dealer Hand");
        dealerHandLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        dealerHandLabel.setTextFill(Color.WHITE);
        dealerHandValueLabel = new Label("Dealer Value: ");
        dealerHandValueLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        dealerHandValueLabel.setTextFill(Color.WHITE);
        dealerHandValueBox = new HBox(200, dealerHandLabel, dealerHandValueLabel);
        dealerHandValueBox.setAlignment(Pos.CENTER_LEFT);
        dealerHandValueBox.setPadding(new Insets(10, 0, 0, 200));
        
        playerCardBox = new HBox();
        playerCardBox.setAlignment(Pos.CENTER_LEFT);
        playerCardBox.setPadding(new Insets(10));

        dealerCardBox = new HBox();
        dealerCardBox.setAlignment(Pos.CENTER_LEFT);
        dealerCardBox.setPadding(new Insets(10));
        
        gameResultLabel = new Label("");
        gameResultLabel.setFont(Font.font("Arial", FontWeight.BOLD, 16));
        gameResultLabel.setTextFill(Color.WHITE);
        gameResultBox = new HBox(gameResultLabel);
        gameResultBox.setAlignment(Pos.CENTER);
        gameResultBox.setPadding(new Insets(20, 0, 0, 500));
        
        playerScoreLabel = new Label("Player Score: " + playerScore);
        playerScoreLabel.setTextFill(Color.WHITE);
        playerScoreLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        dealerScoreLabel = new Label("Dealer Score: " + dealerScore);
        dealerScoreLabel.setTextFill(Color.WHITE);
        dealerScoreLabel.setFont(Font.font("Arial", FontWeight.BOLD, 14));
        values = new VBox(10, playerScoreLabel, dealerScoreLabel);
        
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
        
        buttonBox = new HBox(10, startButton, hitButton, standButton);
        buttonBox.setAlignment(Pos.CENTER_LEFT);
        buttonBox.setPadding(new Insets(0, 0, 0, 320));
        
        GridPane grid = new GridPane();
        grid.add(dealerHandValueBox, 0, 0);
        grid.add(dealerCardBox, 0, 1);
        dealerCardBox.setPrefSize(600, 300);
        grid.add(playerHandValueBox, 0, 2);
        grid.add(playerCardBox, 0, 3);
        playerCardBox.setPrefSize(600, 300);
        grid.add(buttonBox, 0, 5);
        grid.add(values, 0, 6);
        grid.add(gameResultBox, 0, 6, 3, 1);


        grid.setStyle("-fx-background-color: forestgreen");

        
        Scene scene = new Scene(grid);
        
        primaryStage.setTitle("Blackjack");
        primaryStage.setScene(scene);
        primaryStage.setHeight(850);
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
	    	//mac /Users/timothy_bogun/eclipse-workspace/Project2/cards/10.png
	    	Image image = new Image("file:/Users/timothy_bogun/eclipse-workspace/Project2/cards/"+card.getFace()+".png");
	        ImageView iv = new ImageView(image);
	        box.getChildren().add(iv);
	    }
	    handValue.setText("Value: "+String.valueOf(p.valueOfHand()));
	}
	private void endGame() 
	{
	    hitButton.setDisable(true);
	    standButton.setDisable(true);
	    int playerHandValue = player.valueOfHand();
	    int dealerHandValue = dealer.valueOfHand();
	    if (playerHandValue > 21) {
	        gameResultLabel.setText("Player Bust! Dealer Wins!");
	        dealerScore++;
	    } else if (dealerHandValue > 21) {
	        gameResultLabel.setText("Dealer Bust! Player Wins!");
	        playerScore++;
	    } else if (playerHandValue > dealerHandValue) {
	        gameResultLabel.setText("Player Wins!");
	        playerScore++;
	    } else if (dealerHandValue > playerHandValue) {
	        gameResultLabel.setText("Dealer Wins!");
	        dealerScore++;
	    } else {
	        gameResultLabel.setText("Push! No one wins.");
	    }
	    startButton.setDisable(false);
	}
	public static void main(String[] args) 
	{
		launch(args);
	}
}
