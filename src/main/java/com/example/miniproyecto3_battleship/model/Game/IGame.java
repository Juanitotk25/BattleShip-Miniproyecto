package com.example.miniproyecto3_battleship.model.Game;

import com.example.miniproyecto3_battleship.model.Player.IPlayer;
import com.example.miniproyecto3_battleship.model.Player.PlayerBot;
import com.example.miniproyecto3_battleship.model.Player.PlayerPerson;

import java.io.Serializable;

public interface IGame extends Serializable {

    //retorna el object que representa al bot
    PlayerBot getPlayerBot();

    //actualiza a nueva instancia de playerbot
    void setPlayerBot(PlayerBot playerBot);

    //retorna el object que representa la instancia de usuario
    PlayerPerson getPlayerPerson();

    //verifica si el jguador ha ganado y retorna true si ganó
    boolean verifyWinner(IPlayer player);
}
