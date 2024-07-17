package io.dogsbean.game;

import io.dogsbean.MiniGames;
import io.dogsbean.game.commands.GameState;
import io.dogsbean.games.GameType;
import io.dogsbean.utils.TaskUtils;
import lombok.Getter;
import org.bukkit.entity.Player;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicBoolean;

@Getter
public class Game {

    private GameState state;
    private List<UUID> players;
    private GameType type;

    public Game(GameState state, List<UUID> players, GameType gameType) {
        this.state = state;
        this.players = players;
        this.type = gameType;
    }

    public void setUpPlayers(Player player) {
        String gameId = type.getId() + "_" + UUID.randomUUID(); // Aooni ? "0_123DFS" : "1_123DFS";
        MiniGames.getInstance().getGameManager().putInGameMap(gameId, player.getUniqueId());

        player.getInventory().clear();

        player.setHealth(20);
        player.setSaturation(20);
        player.setFoodLevel(20);

        AtomicBoolean isCancelled = TaskUtils.sendMessageEverySecondDuring(1, player, type.getDisplayColor() + type.getName() + " starting soon..", state == GameState.IN_COUNT);

        if (isCancelled.get()) {
            // Start the game
            state = GameState.PLAYING;
        } else {
            state = GameState.IN_COUNT;
        }
    }
}
