package userinterface;

import constants.Constants.Face;
import constants.Constants.Suit;
import core.Card;import java.awt.Image;
import java.io.FileNotFoundException;
import java.net.URL;import javax.swing.ImageIcon;
import javax.swing.JButton;import javax.swing.JComponent;
import javax.swing.JLabel;

/**
 * <h1>Assignment 7: Euchre Part 6</h1>
 * 
 * @author Zach Newsom (with sections provided by Karin Whiting)
 */

public class CardUi
{
    // declare member variables
    private Card card;
    private ImageIcon imageIcon;
    private JButton button;
    private JLabel label;
    private int position;
    
    // constructors
    public CardUi(Card card, JButton button)
    {
        this.card = card;
        this.button = button;
        selectFrontImage(this.button);
    }
    
    public CardUi(Card card, JLabel label, int position)
    {
        this.card = card;
        this.label = label;
        this.position = position;
        
        if (position == 1 || position == 3)
            selectVerticalBackImage();
        else
            selectHorizontalBackImage();
    }
      
    public CardUi(JLabel label)
    {
        this.label = label;
        selectHorizontalBackImage();
    }
    
    public CardUi(JLabel label, Face face, Suit suit)
    {
        this.label = label;
        Card card = new Card();
        card.setFace(face);
        card.setSuit(suit);
        this.card = card;
        selectFrontImage(this.label);
    }

    CardUi()
    {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    private void selectFrontImage(JComponent component)
    {
        String fileName = "../images/";
        
        switch (card.getFace())
        {
            case ACE:fileName += "Ace";
            break;
            case NINE:fileName += "Nine";
            break;
            case TEN:fileName += "Ten";
            break;
            case JACK:fileName += "Jack";
            break;
            case QUEEN:fileName += "Queen";
            break;
            case KING:fileName += "King";
        }
        
        switch (card.getSuit())
        {
            case CLUBS:fileName += "Clubs";
            break;
            case HEARTS:fileName += "Hearts";
            break;
            case DIAMONDS:fileName += "Diamonds";
            break;
            case SPADES:fileName += "Spades";
        }
        
        fileName += ".png";
       
        try
        {
            URL imgURL = getClass().getResource(fileName);
            
            if (imgURL != null)
            {
                imageIcon = new ImageIcon(imgURL);
            }
            
            if (component instanceof JButton)
                button = new JButton(imageIcon);
            else
                label = new JLabel(imageIcon);
        }
        
        catch(Exception ex)
        {
            System.err.println("Couldn't find file: " + fileName);
            imageIcon = null;
        }
    }
    
    private void selectVerticalBackImage()
    {
        String fileName = "../images/backVertical.png";
        
        try
        {
            URL imgURL = getClass().getResource(fileName);
            
            if (imgURL != null)
            {
                imageIcon = new ImageIcon(imgURL);
                Image nextImage = imageIcon.getImage().getScaledInstance(100, 75, java.awt.Image.SCALE_SMOOTH);
                imageIcon = new ImageIcon(nextImage);
                label = new JLabel(imageIcon);
            }
        }
        
        catch(Exception ex)
        {
            System.err.println("Couldn't find file: " + fileName);
            imageIcon = null;
        }
    }
    
    private void selectHorizontalBackImage()
    {
        String fileName = "../images/backHorizontal.png";
        
        try
        {
            URL imgURL = getClass().getResource(fileName);
            
            if (imgURL != null)
            {
                imageIcon = new ImageIcon(imgURL);
                Image nextImage = imageIcon.getImage().getScaledInstance(75, 100, java.awt.Image.SCALE_SMOOTH);
                imageIcon = new ImageIcon(nextImage);
                label = new JLabel(imageIcon);
            }
        }
        
        catch(Exception ex)
        {
            System.err.println("Couldn't find file: " + fileName);
            imageIcon = null;
        }
    }
    
    public JButton getButton()
    {
        return button;
    }
    
    public void setButton(JButton button) 
    {
        this.button = button;
    }
    
    public JLabel getLabel()
    {
        return label;
    }
    
    public void setLabel(JLabel label) 
    {
        this.label = label;
    }
}
