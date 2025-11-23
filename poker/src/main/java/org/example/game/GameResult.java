package org.example.game;

import org.example.player.Player;

public class GameResult {
    public GameOutcome gameOutcome;
    public Player player;

    public GameResult(GameOutcome gameOutcome, Player player){
        this.gameOutcome = gameOutcome;
        this.player = player;
    }
}
