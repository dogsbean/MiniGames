package io.dogsbean.game;

import io.dogsbean.MiniGames;
import io.dogsbean.game.commands.GameState;
import io.dogsbean.games.GameType;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;

public class GameHandler {

    private final MiniGames plugin;
    private Game currentGame;

    public GameHandler(MiniGames plugin) {
        this.plugin = plugin;
    }

    public void startGame(GameType gameType, List<UUID> playerUUIDs) {
        GameState initialState = GameState.IN_COUNT;

        currentGame = new Game(initialState, playerUUIDs, gameType);

        // Set up each player in the game
        for (UUID playerUUID : playerUUIDs) {
            Player player = Bukkit.getPlayer(playerUUID);
            if (player != null) {
                currentGame.setUpPlayers(player);
            }
        }

        if (currentGame.getState() == GameState.PLAYING) {
            Bukkit.broadcastMessage(currentGame.getType().getDisplayColor() + currentGame.getType().getName() + " has started!");
        }
    }

    public Game getCurrentGame() {
        return currentGame;
    }
}
