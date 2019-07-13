package core;

import constants.Constants;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.Scanner;
import javax.swing.JOptionPane;
import userinterface.GameUi;

/**
 * <h1>Assignment 7: Euchre Part 6</h1>
 * 
 * @author Zach Newsom (with sections provided by Karin Whiting)
 */

public final class Game
{
    // member variables
    private Card trump;
    private Player leadPlayer;
    private Player dealer;
    private Player wonTrick;
    private int round;
    private ArrayList<Team> teams;
    private Deck deck;
    private Scanner scan;
    private ArrayList<Player> table;
    private int dealerIdx;
    private int leadIdx;
    private GameUi ui;
    private int trumpCheck;
    private Team trumpTeam;
    
    //custom constructor
    public Game()
    {
        createTeams();
//        outputTeams();
        createDeck();
        setTable();
        dealHand();
        displayHands();
//        play();
    }
    
    private void createTeams()
    {
        // instantiate teams list
        teams = new ArrayList<>();
        
        // creat 2 new teams
        Team teamOne = new Team();  
        Team teamTwo = new Team();
        
        // name the teams
        teamOne.setTeamName("Team One");
        teamTwo.setTeamName("Team Two");
        
        // add to list
        teams.add(teamOne);
        teams.add(teamTwo);
        
        String name = JOptionPane.showInputDialog("Enter your name");
//        scan = new Scanner(System.in);
//        System.out.println("Enter human player name");
//        String name = scan.next();        
        
        HumanPlayer hp = new HumanPlayer();
        hp.setPlayerName(name);
        hp.setGame(this);
        System.out.println("Human Player added to Team One");
        teamOne.getTeam().add(hp);
        
        // create the AI Players and add them to a team
        for(int p = 1; p <= Constants.AI_PLAYERS; p++)
        {
            AIplayer aip = new AIplayer();
            aip.setPlayerName("AI " + p);
            aip.setGame(this);
            // add AI Player to a team
            if(teamOne.getTeam().size() < 2)
                teamOne.getTeam().add(aip);
            else
                teamTwo.getTeam().add(aip);            
        }
    }

    private void outputTeams()
    {      
        for(Team team : teams)
        {      
            team.outputTeam();
        } 
    }
    
    private void setTable()
    {
        table = new ArrayList();
        
        // assign teams
        Team teamOne = teams.get(Constants.ONE);
        Team teamTwo = teams.get(Constants.TWO);
        
        // get players
        Player teamOnePlayerOne = teamOne.getTeam().get(Constants.ONE);
        Player teamOnePlayerTwo = teamOne.getTeam().get(Constants.TWO);
        Player teamTwoPlayerOne = teamTwo.getTeam().get(Constants.ONE);
        Player teamTwoPlayerTwo = teamTwo.getTeam().get(Constants.TWO);
        
        // set table
        table.add(0, teamOnePlayerOne);
        table.add(1, teamTwoPlayerOne);
        table.add(2, teamOnePlayerTwo);
        table.add(3, teamTwoPlayerTwo);
        
        System.out.println("************************");
        System.out.println("Players at the table are");
        System.out.println("************************");

        for(Player player : table)
        {
            System.out.println(player.getPlayerName());
        }
    }
    
    private void setDealerAndLead()
    {
        // select the first dealer
        Random random = new Random();
        dealerIdx = random.nextInt(Constants.NUM_PLAYERS);
        dealer = table.get(dealerIdx);  
        
        if(dealerIdx < 3)
            leadIdx = dealerIdx + 1;
        else
            leadIdx = 0;
        
        leadPlayer = table.get(leadIdx);
    }
    
    private void dealHand()
    {
        setDealerAndLead();
        
        System.out.println("********************************");
        System.out.println("          DEALING THE HAND");
        System.out.println("********************************");

        System.out.println("Player " + dealer.getPlayerName() + " will deal the hand");

        int playerIdx = leadIdx;
        
        Iterator<Card> currentCard = deck.getCardList().iterator();
        
//        System.out.println("********************************");
//        System.out.println("   FIRST DEAL, TWO CARDS EACH");
//        System.out.println("********************************");

        for(int p = 0; p < Constants.NUM_PLAYERS; p++)
        {
            dealOne(playerIdx, currentCard);
            
            // increment the player index until value of 3, then reset to 0
            if(playerIdx == 3)
                playerIdx = 0;
            else
                playerIdx++;
        }

//        System.out.println("********************************");
//        System.out.println("   SECOND DEAL, THREE CARDS EACH");
//        System.out.println("********************************");

        for(int p = 0; p < Constants.NUM_PLAYERS; p++)
        {
            dealTwo(playerIdx, currentCard);
            
            // increment the player index until value of 3, then reset to 0
            if(playerIdx == 3)
                playerIdx = 0;
            else
                playerIdx++;        
        }
        
        // set trump to next card on deck
        trump = currentCard.next();
        
        System.out.println("********************************");
        System.out.println("Trump card is " + trump.getFace() + " of " + 
                            trump.getSuit() + " color " + trump.getColor());
        System.out.println("********************************");
    }

    public Card getTrump()
    {
        return trump;
    }
    
    private void dealOne(int playerIdx, Iterator<Card> currentCard)
    {       
        for(int c = 0; c < Constants.DEAL_ONE; c++)
        {            
            if(currentCard.hasNext())
            {
                Card card = currentCard.next();

//                System.out.println("Dealing " + card.getFace() + " of " + 
//                                    card.getSuit() + " to player " + 
//                                    table.get(playerIdx).getName());
                
                // add card to a player's hand
                table.get(playerIdx).getHand().add(card);
                // remove the card from the deck after it has been dealt
                currentCard.remove();
            }
        }
    }        
    
    private void dealTwo(int playerIdx, Iterator<Card> currentCard)
    {
        for(int c = 0; c < Constants.DEAL_TWO; c++)
        {            
            if(currentCard.hasNext())
            {
                Card card = currentCard.next();

//                System.out.println("Dealing " + card.getFace() + " of " + 
//                                    card.getSuit() + " to player " + 
//                                    table.get(playerIdx).getName());
                
                // add card to a player's hand
               table.get(playerIdx).getHand().add(card);

                // remove the card from the deck after it has been dealt
                currentCard.remove();
            }
        }       
    }
    
    private void displayHands()
    {
        for(Team team : teams)
        {
            team.outputHands();
        }
    }
    
    private void createDeck()
    {
        deck = new Deck();  
    }
    
    public void play()
    {

        trumpCheck();
    }
    
    public void trumpCheck()
    {
        trumpCheck = 0;
        int currentPlayer = leadIdx;
        
        while (trumpCheck < Constants.NUM_PLAYERS)
        {
            Player newPlayer = table.get(currentPlayer);
            newPlayer.makeTrump();
            
            if(newPlayer.getAcceptTrump())
            {
                for (Team team : teams)
                {
                    if (team.getTeam().contains(newPlayer))
                    {
                        trumpTeam = team;
                        JOptionPane.showMessageDialog(this.getGameUi().getFrame(), trumpTeam.getTeamName() + " has called trump");
                    }
                }
                break;
            }
            else
            {
                trumpCheck++;
            }
            
            currentPlayer++;
            if (currentPlayer > 3)
                currentPlayer = 0;
        }
    }
    
    // Getters and Setters
    public GameUi getGameUi()
    {
        return ui;
    }
    
    public void setGameUi(GameUi ui)
    {
        this.ui = ui;
    }
    
    /**
     * @return the wonTrick
     */
    public Player getWonTrick() 
    {
        return wonTrick;
    }

    /**
     * @param wonTrick the wonTrick to set
     */
    public void setWonTrick(Player wonTrick) 
    {
        this.wonTrick = wonTrick;
    }

    /**
     * @return the round
     */
    public int getRound() 
    {
        return round;
    }

    /**
     * @param round the round to set
     */
    public void setRound(int round) 
    {
        this.round = round;
    }

    /**
     * @return the leadPlayer
     */
    public Player getLeadPlayer() 
    {
        return leadPlayer;
    }

    /**
     * @param leadPlayer the leadPlayer to set
     */
    public void setLeadPlayer(Player leadPlayer) 
    {
        this.leadPlayer = leadPlayer;
    }

    /**
     * @return the dealer
     */
    public Player getDealer() 
    {
        return dealer;
    }

    /**
     * @param dealer the dealer to set
     */
    public void setDealer(Player dealer) 
    {
        this.dealer = dealer;
    }

    /**
     * @return the table
     */
    public ArrayList<Player> getTable() 
    {
        return table;
    }
    
    public ArrayList<Team> getTeams()
    {
        return teams;
    }

    public int getTrumpCheck() {
        return trumpCheck;
    }
    
}
