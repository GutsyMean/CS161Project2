package blackJackGame;

import java.util.ArrayList;
import java.util.Random;

public class Player 
{
	private ArrayList<Card> hand;
	private static Deck deck;
	
	public Player()
	{
		hand = new ArrayList<Card>();
		deck = new Deck();
	}
	public ArrayList<Card> getHand()
	{
		return hand;
	}
	public int valueOfHand()
	{
		int value = 0;
		int aceCount = 0;
		for (Card card : hand)
		{
			value += card.valueOf();
			if (card.getFace().equals("A"))
			{
				aceCount++;
			}
		}
		while (aceCount > 0 && value > 21)
		{
			value -= 10;
			aceCount--;
		}
		return value;
	}
	public void clearHand()
	{
		hand.clear();
	}
	public boolean stand(int otherPlayerValue)
	{
		int value = valueOfHand();
		if (value > otherPlayerValue)
		{
			return true;
		}
		else if (value >= 19)
		{
			return true;
		}
		else if (value >= 16)
		{
			Random random = new Random();
			return random.nextBoolean();
		}
		else
		{
			return false;
		}
	}
	public void hit()
	{
		hand.add(deck.dealCard());
	}
	public boolean bust()
	{
		return valueOfHand() > 21;
	}
}
