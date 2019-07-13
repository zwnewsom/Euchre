package userinterface;

import constants.Constants;
import core.Game;
import core.Player;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import javax.swing.BorderFactory;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

/**
 * <h1>Assignment 6\7: Euchre Part 6</h1>
 * 
 * @author Zach Newsom (with sections provided by Karin Whiting)
 */

public class GameUi
{
    Game game;
    JFrame frame;
    JPanel aiOnePanel, tablePanel, aiTwoPanel, 
           hpPanel, aiThreePanel, northPanel, scorePanel,
           trumpPanel;
    JMenuBar menuBar;
    JMenu gameMenu, helpMenu;
    JMenuItem newGameMenuItem, exitMenuItem, aboutMenuItem,
              rulesMenuItem;
    JLabel teamOneScoreLbl, teamOneScore, teamTwoScoreLbl,
           teamTwoScore, trumpCard;
    ArrayList<JLabel> cards;
    
    public GameUi(Game eucher)
    {
        this.game = eucher;
        initComponents();
        
        eucher.setGameUi(this);
        eucher.play();
    }
    
    
    private void initComponents()
    {
        menuBar = new JMenuBar();
        
        // game menu setup
        gameMenu = new JMenu("Game");
        newGameMenuItem = new JMenuItem("New Game");
        newGameMenuItem.addActionListener(new NewGameListener());
        exitMenuItem = new JMenuItem("Exit");
        exitMenuItem.addActionListener(new ExitListener());
        
        // help menu setup
        helpMenu = new JMenu("Help");
        aboutMenuItem = new JMenuItem("About");
        aboutMenuItem.addActionListener(new AboutListener());   
        rulesMenuItem = new JMenuItem("Game Rules");
        rulesMenuItem.addActionListener(new RulesListener());  
        
        // consolidate onto menu
        gameMenu.add(newGameMenuItem);
        gameMenu.add(exitMenuItem);
        
        helpMenu.add(aboutMenuItem);
        helpMenu.add(rulesMenuItem);
        
        menuBar.add(gameMenu);
        menuBar.add(helpMenu); 
        
        // jframe setup
        frame = new JFrame("Euchre");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(700, 700);
        
        // player panels setup
        aiOnePanel = new AiPlayerUi(game.getTable().get(Constants.POSITION_2), Constants.POSITION_2, this);
        aiTwoPanel = new AiPlayerUi(game.getTable().get(Constants.POSITION_3), Constants.POSITION_3, this);        
        aiThreePanel = new AiPlayerUi(game.getTable().get(Constants.POSITION_4), Constants.POSITION_4, this);
        hpPanel = new HumanPlayerUi(game.getTable().get(Constants.POSITION_1), this);
        
        game.getTable().get(Constants.POSITION_1).setUi(hpPanel);
        game.getTable().get(Constants.POSITION_2).setUi(aiOnePanel);
        game.getTable().get(Constants.POSITION_3).setUi(aiTwoPanel);
        game.getTable().get(Constants.POSITION_4).setUi(aiThreePanel);
        
        // north panel setup
        northPanel = new JPanel();
        northPanel.setMinimumSize(new Dimension(680, 170));
        northPanel.setPreferredSize(new Dimension(680, 170));  
        
        // score panel setup
        scorePanel = new JPanel();
        scorePanel.setBorder(BorderFactory.createTitledBorder("Scores"));
        scorePanel.setMinimumSize(new Dimension(130, 160));
        scorePanel.setPreferredSize(new Dimension(130, 160));
        scorePanel.setLayout(new GridLayout(2,2));
        teamOneScoreLbl = new JLabel("Team One");
        teamOneScore = new JLabel(" " + game.getTeams().get(Constants.ONE).getScore());
        teamTwoScoreLbl = new JLabel("Team Two");
        teamTwoScore = new JLabel(" " + game.getTeams().get(Constants.TWO).getScore());
        scorePanel.add(teamOneScoreLbl);
        scorePanel.add(teamOneScore);
        scorePanel.add(teamTwoScoreLbl);
        scorePanel.add(teamTwoScore);
        
        // trump panel setup
        trumpPanel = new JPanel();
        trumpPanel.setBorder(BorderFactory.createTitledBorder("Trump"));
        trumpPanel.setMinimumSize(new Dimension(130, 160));
        trumpPanel.setPreferredSize(new Dimension(130, 160));
        trumpCard = new JLabel(); 
        CardUi cardUi = new CardUi(trumpCard, game.getTrump().getFace(), game.getTrump().getSuit());
        trumpCard = cardUi.getLabel();
        trumpCard.setMinimumSize(new Dimension(100,120));
        trumpCard.setPreferredSize(new Dimension(100,120));
        trumpCard.setMaximumSize(new Dimension(100,120));
        trumpCard.putClientProperty("face", game.getTrump().getFace());
        trumpCard.putClientProperty("suit", game.getTrump().getSuit());
        trumpPanel.add(trumpCard);
       
        aiTwoPanel.setMinimumSize(new Dimension(350, 160));
        aiTwoPanel.setPreferredSize(new Dimension(350, 160));
        
        northPanel.add(scorePanel);
        northPanel.add(aiTwoPanel);
        northPanel.add(trumpPanel);
        
        // table panel setup
        tablePanel = new JPanel();
        tablePanel.setBorder(BorderFactory.createTitledBorder("Euchre"));
        tablePanel.setMaximumSize(new Dimension(300,200));
        tablePanel.setMinimumSize(new Dimension(300,200));
        tablePanel.setPreferredSize(new Dimension(300,200));  
        
        // add panels to frame
        frame.add(aiOnePanel, BorderLayout.WEST);
        frame.add(northPanel, BorderLayout.NORTH);
        frame.add(aiThreePanel, BorderLayout.EAST);
        frame.add(hpPanel, BorderLayout.SOUTH);
        frame.add(tablePanel, BorderLayout.CENTER);
        
        frame.setJMenuBar(menuBar);
        frame.setVisible(true);
    }
        
    private class ExitListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            int result = 0;
            
            if (e.getActionCommand().equals("Exit"))
            {
                result = JOptionPane.showConfirmDialog(frame, "Are sure you want to exit?");
            }
            
            if (result == JOptionPane.YES_OPTION)
            {
                System.exit(0);
            }
        }
    }
    
    private class AboutListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (e.getActionCommand().equals("About"))
            {
                JOptionPane.showMessageDialog(frame, "Euchre ver. 1\nDeveloper: Zach Newsom\nDate Developed: June 8, 2018");
            }
        }
    }
    
    private class RulesListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e)
        {
            if (e.getActionCommand().equals("Game Rules"))
            {
                
            }
        }
        
    }
    
    private class NewGameListener implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent ae) 
        {
        }

    }
    
    public JFrame getFrame()
    {
        return frame;
    }
}
