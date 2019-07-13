package userinterface;

import constants.Constants;
import core.AIplayer;
import core.Player;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * <h1>Assignment 7: Euchre Part 6</h1>
 * 
 * @author Zach Newsom (with sections provided by Karin Whiting)
 */

public class AiPlayerUi extends JPanel
{
    private AIplayer ai;
    private int position;
    private ArrayList<JLabel> cards;
    private GameUi gameUi;
    private CardUi cardUi;
    int width, height;
    
    public AiPlayerUi(Player player, int position, GameUi gameUi)
    {
       this.ai = (AIplayer)player;
       this.position = position;
       this.gameUi = gameUi;
       
       initComponents();
    }
    
    private void initComponents()
    {
        this.setBorder(BorderFactory.createTitledBorder(ai.getPlayerName()));
        this.setMinimumSize(new Dimension(200, 250));
        this.setPreferredSize(new Dimension(200, 250));
       
                
        cards = new ArrayList<JLabel>();

        if (position == 1 || position == 3)
        {
            this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
            this.width = 100;
            this.height = 50;
        }
        else
        {
            this.setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
            this.width = 50;
            this.height = 100;        
        }
        
        displayCards();
    }
    
    private void displayCards()
    {
   
        for (int i = 0; i < Constants.CARDS; i++)
        {
            JLabel cardLabel = new JLabel(); 
            cardUi = new CardUi(ai.getHand().get(i), cardLabel, position);
            cardLabel = cardUi.getLabel();
            cardLabel.setMinimumSize(new Dimension(width, height));
            cardLabel.setPreferredSize(new Dimension(width, height));
            
            cardLabel.setBorder(BorderFactory.createLineBorder(Color.BLACK));

            cards.add(cardLabel);
            this.add(cardLabel);
        }
    }
}
