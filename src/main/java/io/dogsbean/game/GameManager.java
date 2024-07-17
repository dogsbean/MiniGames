package io.dogsbean.game;

import io.dogsbean.MiniGames;
import org.bukkit.entity.Player;

import java.util.*;

public class GameManager {

    private final MiniGames plugin;
    private HashMap<String, List<UUID>> inGameMap;

    public GameManager(MiniGames plugin) {
        this.plugin = plugin;
        inGameMap = new HashMap<>();
    }

    public void putInGameMap(String gameID, UUID playerID) {
        if (!inGameMap.containsKey(gameID)) {
            inGameMap.put(gameID, new ArrayList<>());
        }

        List<UUID> players = inGameMap.get(gameID);
        if (!players.contains(playerID)) {
            players.add(playerID);
        }
    }

    public void removeInGameMap(String gameID, UUID playerID) {
        if (inGameMap.containsKey(gameID)) {
            List<UUID> players = inGameMap.get(gameID);
            players.remove(playerID);

            if (players.isEmpty()) {
                inGameMap.remove(gameID); //TODO: make 'checkEnded' method
            }
        }
    }

    public Integer getIDFromGameId(String gameId) {
        String[] parts = gameId.split("_");
        if (parts.length == 2) {
            try {
                return Integer.parseInt(parts[0]);
            } catch (NumberFormatException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
