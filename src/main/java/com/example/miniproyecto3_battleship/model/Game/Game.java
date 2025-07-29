package com.example.miniproyecto3_battleship.model.Game;

import com.example.miniproyecto3_battleship.model.Player.IPlayer;
import com.example.miniproyecto3_battleship.model.Player.PlayerBot;
import com.example.miniproyecto3_battleship.model.Player.PlayerPerson;
import com.example.miniproyecto3_battleship.model.ships.*;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Represents the core logic of the Battleship game, managing players, ships,
 * and gameplay operations. Implements the {@code IGame} interface.
 *
 * <p>This class handles player interactions, ship management, and game rules
 * such as determining the winner.</p>
 *
 * <p>Players include a bot ({@code PlayerBot}) and a human player ({@code PlayerPerson}).
 * Ship configurations are stored and manipulated using a list of positions.</p>
 *
 * @author Juan David Lopez Vanegas
 */
public class Game implements IGame {

    public PlayerBot playerBot = new PlayerBot();

    public PlayerPerson playerPerson = new PlayerPerson();

    public PlayerBot getPlayerBot(){
        return playerBot;
    }

    public void setPlayerBot(PlayerBot playerBot){
        this.playerBot = playerBot;
    }

    public PlayerPerson getPlayerPerson(){
        return playerPerson;
    }

    public boolean verifyWinner(IPlayer player){
        return player.verifyWinner();
    }


}