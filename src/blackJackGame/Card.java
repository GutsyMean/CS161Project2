package blackJackGame;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Card extends ImageView
{
	private static String[] FACES = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};
	private static int HEIGHT = 130;
	
	private String face; 
	
	public Card(String face)
	{	
		Image image = new Image(face);
		ImageView imageView = new ImageView(image);
		
		imageView.setFitHeight(HEIGHT);
		imageView.setPreserveRatio(true);
	}
	
	public String getFace()
	{
		return face;
	}
	
	public int valueOf()
	{	
		if (face.equals("A")) {
            return 11;
        } else if (face.equals("K") || face.equals("Q") || face.equals("J") || face.equals("10")) {
            return 10;
        } else {
            return Integer.parseInt(face);
        }
	}

	public static String[] getFACES() 
	{
		return FACES;
	}
}
