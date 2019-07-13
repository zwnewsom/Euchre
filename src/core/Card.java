package core;

// explicitly import each enumeration
import constants.Constants.Color;
import constants.Constants.Face;
import constants.Constants.Suit;

/**
 * <h1>Assignment 7: Euchre Part 6</h1>
 * 
 * @author Zach Newsom
 */

public class Card
{
    // declare member variables
    private Face face;
    private Suit suit;
    private Color color;
    
    // added hashCode() method
    public int hashCode()
    {
        int hashCode = 0;
        
        hashCode += face.hashCode();
        hashCode += suit.hashCode();
        hashCode += color.hashCode();
        
        return hashCode;
    }
    
    // added equals() method
    public boolean equals(Object obj)
    {
        // check if object is instance of Card return true if face, suit, and color match
        if (obj instanceof Card)
        {
            Card card = (Card)obj;
            return (card.face.equals(this.face) && 
                    card.color.equals(this.color) && 
                    card.suit.equals(this.suit));
        }
        
        else
            return false;
    }

    // getters and setters
    public Face getFace()
    {
        return face;
    }

    public void setFace(Face face)
    {
        this.face = face;
    }

    public Suit getSuit()
    {
        return suit;
    }

    public void setSuit(Suit suit)
    {
        this.suit = suit;
    }

    public Color getColor()
    {
        return color;
    }

    public void setColor(Color color)
    {
        this.color = color;
    }
}
