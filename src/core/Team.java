package core;

import java.util.ArrayList;

/**
 * <h1>Assignment 7: Euchre Part 6</h1>
 * 
 * @author Zach Newsom (with sections provided by Karin Whiting)
 */

public class Team
{
    ArrayList<Player> team = new ArrayList<>();
    int score;
    int tricks;
    String teamName;

    public ArrayList<Player> getTeam()
    {
        return team;
    }

    public void setTeam(ArrayList<Player> team)
    {
        this.team = team;
    }

    public int getScore()
    {
        return score;
    }

    public void setScore(int score)
    {
        this.score = score;
    }

    public int getTricks()
    {
        return tricks;
    }

    public void setTricks(int tricks)
    {
        this.tricks = tricks;
    }

    public String getTeamName()
    {
        return teamName;
    }

    public void setTeamName(String teamName)
    {
        this.teamName = teamName;
    }
    
    public Team()
    {
        ArrayList<Player> p = new ArrayList<>();
    }
    
    public void outputTeam()
    {
        for (Player player : team)
        {
            System.out.printf("Player: %s\n", player.getPlayerName());
        }
    }
    
    // added for pt 3
    public void outputHands()
    {
        // loop through players in each team, print player name then hand
        for (Player player : team)
        {
            if (player instanceof HumanPlayer)
            {
                System.out.println("********************\n"
                               + "Player " + player.getPlayerName() + " hand is\n"
                               + "********************");
                player.displayHand();
            }
        }
    }
}
