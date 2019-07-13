package userinterface;

import constants.Constants;
import core.HumanPlayer;
import core.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * <h1>Assignment 6: Euchre Part 5</h1>
 * 
 * @author Zach Newsom (with sections provided by Karin Whiting)
 */

public class HumanPlayerUi extends JPanel
{
   private HumanPlayer human;
    private ArrayList<JButton> cards;
    private CardUi cardUi;
    private GameUi gameUi;
    private JFrame parent;
    private Constants.Suit suit;
    
    public HumanPlayerUi(Player player, GameUi gameUi)
    {
        human = (HumanPlayer)player;
        this.gameUi = gameUi;
        
        initComponents();
    }
    
    private void initComponents()
    {
        this.setBorder(BorderFactory.createTitledBorder(human.getPlayerName()));
        this.setMinimumSize(new Dimension(250, 150));
        this.setPreferredSize(new Dimension(250, 150));
        
        this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));

        displayCards();
    }
    
    private void displayCards()
    {                        
        cards = new ArrayList<JButton>();

        for(int i = 0; i < Constants.CARDS; i++)
        {
            // instantiate the JButton
            JButton card = new JButton();
            cardUi = new CardUi(human.getHand().get(i), card);
            card = cardUi.getButton();
            // update the JButton and format it
            card.setMinimumSize(new Dimension(60,100));
            card.setPreferredSize(new Dimension(60,100));
            card.setBorder(BorderFactory.createLineBorder(Color.BLACK));
            // card.setText("Card" + c);
            // add the object to the the ArrayList and UI
            cards.add(card);
            
            for(JButton button : cards)
                this.add(button);
        }
    }
}
