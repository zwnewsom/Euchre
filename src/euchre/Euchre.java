package euchre;

import core.Game;
import javax.swing.JOptionPane;
import userinterface.GameUi;

/**
 * <h1>Assignment 7: Euchre Part 6</h1>
 * 
 * @author Zach Newsom (with sections provided by Karin Whiting)
 */

public class Euchre
{
    public static void main(String[] args)
    {
        JOptionPane.showMessageDialog(null, "Let's Play Euchre!");
        
        Game euchre = new Game();
        GameUi euchreUi = new GameUi(euchre);
    }
    
}
