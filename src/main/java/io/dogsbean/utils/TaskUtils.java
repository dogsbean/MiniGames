package io.dogsbean.utils;

import io.dogsbean.MiniGames;
import lombok.experimental.UtilityClass;
import org.bukkit.entity.Player;
import org.bukkit.scheduler.BukkitRunnable;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.function.Predicate;

@UtilityClass
public class TaskUtils {

    public void sendMessageEverySecond(int second, Player player, String message) {
        new BukkitRunnable() {
            @Override
            public void run() {
                player.sendMessage(message);
            }
        }.runTaskTimer(MiniGames.getInstance(), 0L, second * 20L);
    }

    public void sendMessageAfterSecond(int second, Player player, String message) {
        new BukkitRunnable() {
            @Override
            public void run() {
                player.sendMessage(message);
            }
        }.runTaskLater(MiniGames.getInstance(), second * 20L);
    }

    public void sendMessageAfterSecondAndStopIf(int second, Player player, String message, Predicate<Player> stopCondition) {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (stopCondition.test(player)) {
                    cancel();
                    return;
                }
                player.sendMessage(message);
            }
        }.runTaskLater(MiniGames.getInstance(), second * 20L);
    }

    public void sendMessageEverySecondAndStopIf(int second, Player player, String message, Predicate<Player> stopCondition) {
        new BukkitRunnable() {
            @Override
            public void run() {
                if (stopCondition.test(player)) {
                    cancel();
                    return;
                }

                player.sendMessage(message);
            }
        }.runTaskTimer(MiniGames.getInstance(), 0L, second * 20L);
    }

    public AtomicBoolean sendMessageEverySecondDuring(int second, Player player, String message, boolean stopCondition) {
        AtomicBoolean isCancelled = new AtomicBoolean(false);
        new BukkitRunnable() {
            @Override
            public void run() {
                if (!stopCondition) {
                    isCancelled.set(true);
                    cancel();
                    return;
                }
                player.sendMessage(message);
            }
        }.runTaskTimer(MiniGames.getInstance(), 0L, 20L);

        return isCancelled;
    }
}
