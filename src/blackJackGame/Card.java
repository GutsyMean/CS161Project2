package blackJackGame;

import javafx.scene.image.ImageView;

public class Card 
{
	private static String[] FACES = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
	private static int HEIGHT = 130;
	
	private String face; 
	
	public Card(String face)
	{
		ImageView image = new ImageView(face);
		
		image.setFitHeight(HEIGHT);
		image.setPreserveRatio(true);
	}
	
	public String getFace()
	{
		return face;
	}
	
	public int valueOf()
	{	
		for (String face : FACES)
			if (face == "10" || face == "J" || face == "Q" || face == "K")
				return 10;
			else if (face == "2")
				return 2;
			else if (face == "3")
				return 3;
			else if (face == "4")
				return 4;
			else if (face == "5")
				return 5;
			else if (face == "6")
				return 6;
			else if (face == "7")
				return 7;
			else if (face == "8")
				return 8;
			else if (face == "9")
				return 9;
			return 11;
	}
}
