package cn.encmys.ykdz.forest.realthirst.schedule;

import cn.encmys.ykdz.forest.realthirst.RealThirst;
import cn.encmys.ykdz.forest.realthirst.player.ThirstPlayer;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.*;

public class ThirstSchedule {
    private final RealThirst plugin;
    private final List<UUID> cancelSprinting = new ArrayList<>();

    public ThirstSchedule(RealThirst plugin) {
        this.plugin = plugin;
        runSprintBlocker();
    }

    public void runSprintBlocker() {
    }
}
