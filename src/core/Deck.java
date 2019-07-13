package core;

import java.util.Set;
import java.util.HashSet;
import constants.Constants;
import constants.Constants.Face;
import constants.Constants.Suit;
import constants.Constants.Color;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * <h1>Assignment 7: Euchre Part 6</h1>
 * 
 * @author Zach Newsom (with sections provided by Karin Whiting)
 */

public class Deck
{
    // declare member variables
    private Set<Card> deck;
    private List<Card> cardList;
    
    // commented out all console output to make consistent with screenshots
    // in assignment instructions
    public Deck()
    {
//        System.out.println("****************************");
//        System.out.println("Generating the deck of cards");
//        System.out.println("****************************");
        generateDeck();
//        System.out.println("****************************");
//        System.out.println("Displaying the deck of cards");
//        System.out.println("****************************");
        displayDeck();
//        System.out.println("***************************");
//        System.out.println("Shuffling the deck of cards");
//        System.out.println("***************************");
        shuffleDeck();
//        System.out.println("*************************************");
//        System.out.println("Displaying the shuffled deck of cards");
//        System.out.println("*************************************");
        displayDeck();
        shuffleDeck(); // added for pt 4
        displayCardList(); // added for pt 4
    }
    
    private void generateDeck()
    {
        // converts Set to HashSet
        deck = new HashSet<>(Constants.CARDS);
        
        // loop through faces
        for (Face face : Face.values())
        {
            // while looping through suits
            for (Suit suit : Suit.values())
            {
                Card card = new Card();// instance of Card class
                card.setFace(face);    // set the face value
                card.setSuit(suit);    // set the suit
                
                // set diamonds and hearts to red, all others to black
                if (suit == Suit.DIAMONDS || suit == Suit.HEARTS)
                    card.setColor(Color.RED);
                else
                    card.setColor(Color.BLACK);

                if (!deck.contains(card))
                    deck.add(card);
            }// suit loop
        }// face loop
    }
    
    private void displayDeck()
    {
        //System.out.println("Deck size: " + deck.size() + " cards");
        //System.out.println("Deck includes:");
  
        for(Card card : deck)
        {
            System.out.println("Card: " + card.getFace() + " of " + card.getSuit() + " is color " + card.getColor());
        }
    }
    
    private void shuffleDeck()
    {
        this.cardList = new ArrayList<Card>(deck);
        
        // shuffle cards in cardList
        Collections.shuffle(cardList);
        deck = new HashSet<Card>(cardList);
    }
    
    private void displayCardList()
    {
        // for (Card card : cardList)
        {
            // System.out.println("Card Value is " + card.getFace().getValue() + "\n" + 
                               // "Card Face is " + card.getFace() + "\n" + 
                               // "Card Color is " + card.getColor());
        }
    }
    
    // added getters/ setters for pt 4
    public List<Card> getCardList()
    {
        return cardList;
    }

    public void setCardList(List<Card> cardList)
    {
        this.cardList = cardList;
    }
    
    public Set<Card> getDeck()
    {
        return deck;
    }

    public void setDeck(Set<Card> deck)
    {
        this.deck = deck;
    }
}
