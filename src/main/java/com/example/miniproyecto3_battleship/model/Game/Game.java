package com.example.miniproyecto3_battleship.model.Game;

import com.example.miniproyecto3_battleship.model.Player.IPlayer;
import com.example.miniproyecto3_battleship.model.Player.PlayerBot;
import com.example.miniproyecto3_battleship.model.Player.PlayerPerson;
import com.example.miniproyecto3_battleship.model.ships.*;

import java.io.Serializable;
import java.util.ArrayList;

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