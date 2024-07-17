package io.dogsbean.games;

import lombok.Getter;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.PortalType;

@Getter
public enum GameType {

    AOONI(0,"Aooni", "Hide and run to escape!", ChatColor.DARK_PURPLE, Material.PORTAL),
    SPOON_MURDERER(1, "Spoon Murderer", "Hide and run to escape the murderer's mansion!", ChatColor.DARK_RED, Material.STONE_SPADE);

    private final Integer id;
    private final String name, description;
    private final ChatColor displayColor;
    private final Material icon;

    GameType(Integer id, String name, String description, ChatColor displayColor, Material icon) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.displayColor = displayColor;
        this.icon = icon;
    }
}
