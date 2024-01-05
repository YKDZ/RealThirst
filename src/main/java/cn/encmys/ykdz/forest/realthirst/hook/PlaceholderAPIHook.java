package cn.encmys.ykdz.forest.realthirst.hook;

import cn.encmys.ykdz.forest.realthirst.RealThirst;
import cn.encmys.ykdz.forest.realthirst.player.ThirstPlayer;
import me.clip.placeholderapi.expansion.PlaceholderExpansion;
import org.bukkit.OfflinePlayer;
import org.bukkit.entity.Player;
import org.jetbrains.annotations.NotNull;

import java.util.Optional;

public class PlaceholderAPIHook extends PlaceholderExpansion {
    private final RealThirst plugin;

    public PlaceholderAPIHook(RealThirst plugin) {
        this.plugin = plugin;
    }

    @Override
    public @NotNull String getAuthor() {
        return "YK_DZ";
    }

    @Override
    public @NotNull String getIdentifier() {
        return "realthirst";
    }

    @Override
    public @NotNull String getVersion() {
        return "1.0.0";
    }

    @Override
    public boolean persist() {
        return true; // This is required or else PlaceholderAPI will unregister the Expansion on reload
    }

    @Override
    public String onRequest(OfflinePlayer player, String params) {
        Optional<Player> optionalPlayer = Optional.ofNullable(player.getPlayer());
        ThirstPlayer thirstPlayer = optionalPlayer.map(ThirstPlayer::new).orElse(null);

        switch (params) {
            case "thirstValue":
                return String.valueOf(thirstPlayer.getThirstValue());
            case "thirstiness":
                return String.valueOf(thirstPlayer.getThirstiness());
            case "aridity":
                return String.valueOf(thirstPlayer.getAridity());
            default:
                return null;
        }
    }
}
