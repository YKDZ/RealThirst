package cn.encmys.ykdz.forest.realthirst.listener;

import cn.encmys.ykdz.forest.realthirst.config.MainConfig;
import cn.encmys.ykdz.forest.realthirst.player.ThirstPlayer;
import org.bukkit.Statistic;
import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerMoveEvent;
import org.bukkit.event.player.PlayerStatisticIncrementEvent;

import java.util.HashMap;

public class PlayerListener implements Listener {

    @EventHandler
    public void onPlayerJoin(PlayerJoinEvent e) {
        ThirstPlayer player = new ThirstPlayer(e.getPlayer());
        player.initThirst();
    }

    @EventHandler
    public void onPlayerJump(PlayerStatisticIncrementEvent e) {
        if(e.getStatistic() != Statistic.JUMP) { return; }
        Player player = e.getPlayer();
        ThirstPlayer thirstPlayer = new ThirstPlayer(player);
        if(player.isSneaking()) {
            thirstPlayer.changeAridity(MainConfig.aridity_actions_jumpingWhenSprinting);
        } else {
            thirstPlayer.changeAridity(MainConfig.aridity_actions_jumping);
        }
    }

    @EventHandler
    public void onPlayerBreakBlock(BlockBreakEvent e) {
        Player player = e.getPlayer();
        ThirstPlayer thirstPlayer = new ThirstPlayer(player);
        thirstPlayer.changeAridity(MainConfig.aridity_actions_breakBlock);
    }

    @EventHandler
    public void onPlayerAttackEntity(EntityDamageByEntityEvent e) {
        Entity entity = e.getDamager();
        if(entity.getType() != EntityType.PLAYER || e.getDamage() == 0) { return; }
        ThirstPlayer thirstPlayer = new ThirstPlayer((Player) entity);
        thirstPlayer.changeAridity(MainConfig.aridity_actions_attackEntity);
    }

    private static HashMap<Player, Double> playerSwimDistance = new HashMap<>();
    @EventHandler void onPlayerSwim(PlayerMoveEvent e) {
        Player player = e.getPlayer();
        if (player.isSwimming()) {
            double distance = e.getFrom().distance(e.getTo());
            double swimDistance = playerSwimDistance.getOrDefault(player, 0.0);
            swimDistance += distance;
            if (swimDistance >= 1.5) {
                ThirstPlayer thirstPlayer = new ThirstPlayer(player);
                thirstPlayer.changeAridity(MainConfig.aridity_actions_swimming);
                swimDistance = 0.0;
            }
            playerSwimDistance.put(player, swimDistance);
        } else {
            playerSwimDistance.remove(player);
        }
    }

    private static HashMap<Player, Double> playerSprintingDistance = new HashMap<>();
    @EventHandler void onPlayerSprinting(PlayerMoveEvent e) {
        Player player = e.getPlayer();
        if (player.isSprinting()) {
            double distance = e.getFrom().distance(e.getTo());
            double sprintingDistance = playerSprintingDistance.getOrDefault(player, 0.0);
            sprintingDistance += distance;
            if (sprintingDistance >= 1.5) {
                ThirstPlayer thirstPlayer = new ThirstPlayer(player);
                thirstPlayer.changeAridity(MainConfig.aridity_actions_sprinting);
                sprintingDistance = 0.0;
            }
            playerSprintingDistance.put(player, sprintingDistance);
        } else {
            playerSprintingDistance.remove(player);
        }
    }
}
