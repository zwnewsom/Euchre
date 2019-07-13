package core;

import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * <h1>Assignment 7: Euchre Part 6</h1>
 * 
 * @author Zach Newsom (with sections provided by Karin Whiting)
 */

public abstract class Player implements IPlayer
{
    private String playerName;
    private int tricks;
    private int score;
    private ArrayList<Card> hand;
    private JPanel ui;
    private boolean acceptTrump;

    @Override
    public abstract Card playCard();
    
    @Override
    public abstract void makeTrump();
    
    public JPanel getUi()
    {
        return ui;
    }

    public void setUi(JPanel ui)
    {
        this.ui = ui;
    }
    
    // getters/ setters for pt 3
    public ArrayList<Card> getHand()
    {
        return hand;
    }

    public void setHand(ArrayList<Card> hand)
    {
        this.hand = hand;
    }
    
    public String getPlayerName()
    {
        return playerName;
    }

    public void setPlayerName(String playerName)
    {
        this.playerName = playerName;
    }
    
    public boolean getAcceptTrump() 
    {
        return acceptTrump;
    }

    public void setAcceptTrump(boolean acceptTrump) 
    {
        this.acceptTrump = acceptTrump;
    }
    
    public int getTricks() 
    {
        return tricks;
    }

    public void setTricks(int tricks) 
    {
        this.tricks = tricks;
    }

    public int getScore() 
    {
        return score;
    }

    public void setScore(int score) 
    {
        this.score = score;
    }
    
    // custom constructor for pt 3
    public Player()
    {
        hand = new ArrayList<>();
    }
    
    // added for pt 3
    public void displayHand()
    {
        // loop through cards in each hand, print output
        for (Card card : hand)
        {
            System.out.println(card.getFace() + " of " + card.getSuit());
        }
    }
    
    // added for pt 4
    public void sortBySuit()
    {
        ArrayList<Card> sortedHand = new ArrayList<>();
        
        while (hand.size() > 0)
        {
            int i = 0;// current position in ArrayList
            Card firstCard = hand.get(0);
            
            for (int j = 1; j < hand.size(); j++)
            {
                Card nextCard = hand.get(j);
                
                if ((nextCard.getSuit().getRank() < firstCard.getSuit().getRank() || nextCard.getSuit() == firstCard.getSuit()) && nextCard.getFace().getValue() < firstCard.getFace().getValue())
                {
                    i = j;
                    firstCard = nextCard;
                }
            }
            
            hand.remove(i);
            sortedHand.add(firstCard);
        }
        
        hand = sortedHand;
    }
}
