package io.dogsbean;

import io.dogsbean.game.GameManager;
import lombok.Getter;
import org.bukkit.plugin.java.JavaPlugin;

public class MiniGames extends JavaPlugin {

    @Getter private static MiniGames instance;
    @Getter private GameManager gameManager;

    @Override
    public void onEnable() {
        instance = this;

        this.gameManager = new GameManager(this);
    }
}
