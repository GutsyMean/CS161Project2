package blackJackGame;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class Deck
{
	private ArrayList<Card> cards;
	
	
	public Deck()
	{
		reset();
	}
	public void reset()
	{
		
		cards = new ArrayList<Card>();
		for (String face : Card.getFACES())
		{
			cards.add(new Card(face));
            cards.add(new Card(face));
            cards.add(new Card(face));
            cards.add(new Card(face));
		}
		shuffle();
	}
	public Card dealCard()
	{
		Random random = new Random();
		return cards.remove(random.nextInt(cards.size()));	
	}
	public void shuffle() 
	{
        Collections.shuffle(cards);
    }
}
